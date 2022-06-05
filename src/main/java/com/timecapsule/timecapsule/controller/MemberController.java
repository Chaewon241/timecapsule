package com.timecapsule.timecapsule.controller;

import com.timecapsule.timecapsule.controller.form.MemberForm;
import com.timecapsule.timecapsule.controller.form.MemberInfoForm;
import com.timecapsule.timecapsule.domain.Group;
import com.timecapsule.timecapsule.domain.GroupMember;
import com.timecapsule.timecapsule.domain.Member;
import com.timecapsule.timecapsule.domain.dto.MemberDto;
import com.timecapsule.timecapsule.service.MemberService;
import com.timecapsule.timecapsule.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MessageService messageService;

    /**
     * 회원가입 폼 띄우기
     * 회원가입을 진행할 페이지로 넘어가게 해줄 매핑입니다.
     * @return : 멤버 생성 페이지로 이동(createMemberForm.jsp)
     */
    @GetMapping("/member/new")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "createMemberForm";
    }

    /**
     * 회원가입 진행
     * 회원가입을 하는 매핑입니다.
     * @param email : 로그인할때 사용하는 이메일
     * @param password : 로그인할때 사용하는 비밀번호
     * @param phoneNumber : 사용자 휴대폰번호
     * @param nickname : 닉네임
     * @return : 로그인 전 메인페이지로 이동
     */
    @PostMapping("/member/new")
    public String create(@RequestParam("email") String email,
                         @RequestParam("password") String password,
                         @RequestParam("phoneNumber") String phoneNumber,
                         @RequestParam("nickname") String nickname){

        Member member = new Member(email, password, phoneNumber, nickname);
        memberService.join(member);
        return "Main";
    }

    /**
     * 회원 정보 조회
     * 회원 정보 조회로 model 에 값을 담아서 넘겨줍니다.
     * @Model : email, nickname, phoneNumber, password, groups
     * @return : info 페이지로 이동
     */
    @GetMapping("/member/info")
    public String memberInfo(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Long memberId = Long.valueOf(String.valueOf(session.getAttribute("memberId")));
        Member findMember = memberService.findOne(memberId);
        List<Group> groups = new ArrayList<>();
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

    /**
     * 회원 탈퇴
     * 회원 탈퇴하는 매핑으로 세션에 답긴 값을 다 지우고, 세션을 초기화합니다.
     * @return : 로그인 전 메인페이지로 이동
     */
    @PostMapping("/member/delete")
    public String deleteMember(HttpServletRequest request){
        HttpSession session = request.getSession();
        Long memberId = Long.valueOf(String.valueOf(session.getAttribute("memberId")));
        memberService.removeMember(memberId);
        return "Main";
    }

    /**
     * 회원 정보 수정 폼 띄우기
     * 회원이 정보를 수정할 때 사용할 페이지로 넘어가게 해주는 매핑입니다.
     * 회원 정보 수정을 눌렀을 때 띄울 초기 값을 model 에 담아서 넘겨줍니다.
     * @Model : info(회원 정보가 다 담김)
     * @return : 회원 정보 수정 페이지로 이동(updateMemberForm)
     */
    @GetMapping("/member/edit")
    public String updateMemberForm(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Long memberId = Long.valueOf(String.valueOf(session.getAttribute("memberId")));
        Member member = memberService.findOne(memberId);
        MemberInfoForm infoForm = new MemberInfoForm(member.getEmail(),
                member.getPassword(), member.getNickname(), member.getPhoneNumber());
        model.addAttribute("info", infoForm);
        return "updateMemberForm";
    }

    /**
     * 회원 정보 수정
     * 회원 정보 수정을 직접적으로 수행하는 매핑으로 값을 전달받아서
     * 직접적으로 수정합니다.
     * @param email : 로그인할때 사용하는 이메일
     * @param password : 로그인할때 사용하는 비밀번호
     * @param phoneNumber : 사용자 휴대폰번호
     * @param nickname : 닉네임
     * @return : 로그인 후 메인페이지로 이동
     */
    @PostMapping("/member/edit")
    public String updateMember(HttpServletRequest request,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password,
                               @RequestParam("phoneNumber") String phoneNumber,
                               @RequestParam("nickname") String nickname
                               ){
        HttpSession session = request.getSession();
        Long memberId = Long.valueOf(String.valueOf(session.getAttribute("memberId")));
        MemberDto memberDto = new MemberDto(email, password, phoneNumber, nickname);
        memberService.updateMemberInfo(memberId, memberDto);
        return "Main2";
    }

    /**
     * 휴대폰 인증
     * 회원가입시에 휴대폰 인증을 수행하는 매핑입니다.
     * authKey 를 반환하는 매핑으로 비동기방식으로 접근해서 값을 전달받아야 합니다.
     * @param phoneNumber : 인증을 수행할 휴대폰 번호
     * @return : authKey(인증키 값을 반환, 이를 이용해서 휴대폰으로 전송된 값과 비교)
     */
    @PostMapping("/member/auth")
    public String phoneAuth(@RequestParam("phoneNumber") String phoneNumber){
        Random random = new Random();
        String authKey = String.valueOf(random.nextInt(8888)+1111);
        messageService.sendAuthMessage(phoneNumber, authKey);
        return authKey;
    }
}
