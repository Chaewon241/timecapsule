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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        return "/createMemberForm.html";
    }

    
    //Post 매핑으로 member/new로 넘어오면 폼의 데이터로 회원가입을 진행함
    //BindingResult에 에러가 있을 시 createMemberForm으로 다시 넘김
    @PostMapping("/member/new")
    public String create(@Valid MemberForm form, BindingResult result){
        if(result.hasErrors()){
            return "/createMemberForm.html";
        }

        Member member = new Member(form.getEmail(), form.getNickname(),
                form.getPhoneNumber(), form.getPassword());

        memberService.join(member);
        return "redirect:/";
    }

    //Get 매핑으로 member/{id}로 접근하면 회원의 데이터를 model에 담아서 보내줍니다.
    //회원의 정보를 출력하는 info 페이지 필요
    @GetMapping("/member/{id}")
    public String memberInfo(@PathVariable("id") Long memberId, Model model){
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

        return "/info.html";
    }

    //Post 매핑으로 member/{id}/delete로 넘어오면 회원을 탈퇴시킵니다.
    //회원탈퇴를 진행할 Post 매핑을 해줄 "회원탈퇴" 버튼이 필요함
    @PostMapping("/member/{id}/delete")
    public String deleteMember(@PathVariable("id") Long memberId){
        memberService.removeMember(memberId);
        return "redirect:/";
    }

    //Get 매핑으로 member/{id}/edit으로 접근하면 회원 정보 수정을 위한 폼을 화면에 출력합니다.
    //수정 전의 정보를 띄울 updateMemberForm 페이지 필요
    @GetMapping("/member/{id}/edit")
    public String updateMemberForm(@PathVariable("id") Long memberId, Model model){
        Member member = memberService.findOne(memberId);
        MemberInfoForm infoForm = new MemberInfoForm(member.getEmail(),
                member.getPassword(), member.getNickname(), member.getPhoneNumber());
        model.addAttribute("form", infoForm);
        return "/updateMemberForm.html";
    }

    //Post 매핑으로 member/{id}/edit으로 넘어오면 form의 데이터로 업데이트 처리합니다.
    @PostMapping("/member/{id}/edit")
    public String updateMember(@PathVariable("id") Long memberId,
                               @ModelAttribute("form") MemberInfoForm form){
        MemberDto memberDto = new MemberDto(form.getEmail(), form.getPassword(),
                form.getNickname(), form.getPhoneNumber());
        memberService.updateMemberInfo(memberId, memberDto);
        return "redirect:/";
    }
}
