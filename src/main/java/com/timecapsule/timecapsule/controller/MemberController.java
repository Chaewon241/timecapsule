package com.timecapsule.timecapsule.controller;

import com.timecapsule.timecapsule.controller.form.MemberForm;
import com.timecapsule.timecapsule.controller.form.MemberInfoForm;
import com.timecapsule.timecapsule.domain.Group;
import com.timecapsule.timecapsule.domain.GroupMember;
import com.timecapsule.timecapsule.domain.Member;
import com.timecapsule.timecapsule.domain.dto.MemberDto;
import com.timecapsule.timecapsule.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    //Get 매핑으로 member/new에 접근하면 회원가입 폼을 보여줍니다.
    //회원가입을 받을 createMemberForm 페이지 필요
    @GetMapping("/member/new")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "createMemberForm";
    }

    
    //Post 매핑으로 member/new로 넘어오면 폼의 데이터로 회원가입을 진행함
    //BindingResult에 에러가 있을 시 createMemberForm으로 다시 넘김
    @PostMapping("/member/new")
    public String create(@RequestParam("email") String email,
                         @RequestParam("password") String password,
                         @RequestParam("phoneNumber") String phoneNumber,
                         @RequestParam("nickname") String nickname){

        Member member = new Member(email, password, phoneNumber, nickname);
        memberService.join(member);
        return "redirect:/";
    }

    //Get 매핑으로 member/{id}로 접근하면 회원의 데이터를 model에 담아서 보내줍니다.
    //회원의 정보를 출력하는 info 페이지 필요
    @GetMapping("/member/info")
    public String memberInfo(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Long memberId = Long.valueOf(String.valueOf(session.getAttribute("memberId")));
        Member findMember = memberService.findOne(memberId);
        List<Group> groups = null;
        for (GroupMember groupMember : findMember.getGroupMembers()) {
            groups.add(groupMember.getGroup());
        }
        model.addAttribute("email", findMember.getEmail());
        model.addAttribute("nickname", findMember.getNickname());
        model.addAttribute("phoneNumber", findMember.getPhoneNumber());
        model.addAttribute("password", findMember.getPassword());
        model.addAttribute("groups", groups);

        return "info";
    }

    //Post 매핑으로 member/{id}/delete로 넘어오면 회원을 탈퇴시킵니다.
    //회원탈퇴를 진행할 Post 매핑을 해줄 "회원탈퇴" 버튼이 필요함
    @PostMapping("/member/delete")
    public String deleteMember(HttpServletRequest request){
        HttpSession session = request.getSession();
        Long memberId = Long.valueOf(String.valueOf(session.getAttribute("memberId")));
        memberService.removeMember(memberId);
        return "redirect:/";
    }

    //Get 매핑으로 member/{id}/edit으로 접근하면 회원 정보 수정을 위한 폼을 화면에 출력합니다.
    //수정 전의 정보를 띄울 updateMemberForm 페이지 필요
    @GetMapping("/member/edit")
    public String updateMemberForm(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Long memberId = Long.valueOf(String.valueOf(session.getAttribute("memberId")));
        Member member = memberService.findOne(memberId);
        MemberInfoForm infoForm = new MemberInfoForm(member.getEmail(),
                member.getPassword(), member.getNickname(), member.getPhoneNumber());
        model.addAttribute("form", infoForm);
        return "updateMemberForm";
    }

    //Post 매핑으로 member/{id}/edit으로 넘어오면 form의 데이터로 업데이트 처리합니다.
    @PostMapping("/member/edit")
    public String updateMember(HttpServletRequest request,
                               @RequestParam("email") String email,
                               @RequestParam("nickname") String nickname,
                               @RequestParam("phoneNumber") String phoneNumber,
                               @RequestParam("password") String password){
        HttpSession session = request.getSession();
        Long memberId = Long.valueOf(String.valueOf(session.getAttribute("memberId")));
        MemberDto memberDto = new MemberDto(email, password, phoneNumber, nickname);
        memberService.updateMemberInfo(memberId, memberDto);
        return "redirect:/";
    }
}
