package com.timecapsule.timecapsule.controller;

import com.timecapsule.timecapsule.controller.form.SignInForm;
import com.timecapsule.timecapsule.domain.Member;
import com.timecapsule.timecapsule.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class SignController {

    private final MemberService memberService;

    //Get 매핑으로 signIn에 접근하면 로그인 폼을 화면에 보여줍니다.
    //로그인 정보를 받기 위한 createSignInForm 사이트가 필요합니다.
    @GetMapping("/signIn")
    public String createForm(Model model){
        model.addAttribute("form", new SignInForm());
        return "/signInForm.html";
    }

    //Post 매핑으로 signIn으로 넘어오면 폼의 데이터들로 로그인을 진행합니다.
    @PostMapping("/signIn")
    public String signIn(@Valid SignInForm form, BindingResult result,
                         HttpServletRequest request){
        if(result.hasErrors()){
            return "/signInForm.html";
        }
        HttpSession session = request.getSession();
        Long memberId = memberService.signIn(form.getEmail(), form.getPassword());
        Member findMember = memberService.findOne(memberId);
        session.setAttribute("memberId", findMember.getId());
        session.setAttribute("SIGNIN", "TRUE");
        return "redirect:/";
    }

    //Get 매핑으로 signOut으로 접근하면 로그아웃을 진행합니다.
    //세션에 저장된 속성 값을 지우고, 세션을 초기화합니다.
    @GetMapping("/signOut")
    public String signOut(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("memberId");
        session.removeAttribute("SIGNIN");
        session.invalidate();
        return "redirect:/";
    }
}
