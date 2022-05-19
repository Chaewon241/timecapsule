package com.timecapsule.timecapsule.controller;

import com.timecapsule.timecapsule.domain.Member;
import com.timecapsule.timecapsule.service.MemberService;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /*
        get형식으로 /member/new에 member/createMemberForm으로 넘겨서 회원가입폼으로 가게 함
     */
    @GetMapping("/member/new")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "member/createMemberForm";
    }

    /*
        Post형식으로 /member/new에 넘어오면 form에 입력한 값을 가지고 회원가입을 진행함
     */
    @PostMapping("/member/new")
    public String create(@Valid MemberForm form, BindingResult result){
        if(result.hasErrors()){
            return "members/createMemberForm";
        }

        Member member = new Member(form.getEmail(), form.getNickname(),
                form.getPhoneNumber(), form.getPassword());

        memberService.join(member);
        return "redirect:/";
    }
}
