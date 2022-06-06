package com.timecapsule.timecapsule.controller;

import com.timecapsule.timecapsule.controller.form.SignInForm;
import com.timecapsule.timecapsule.domain.Member;
import com.timecapsule.timecapsule.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;

@Controller
@RequiredArgsConstructor
public class SignController {

    private final MemberService memberService;
    
    /**
     * 로그인 폼 띄우기
     * 로그인을 하기 위한 페이지로 이동합니다.
     * @return : 로그인 페이지(signInForm)
     */
    @GetMapping("/signIn")
    public String createForm(Model model){
        model.addAttribute("form", new SignInForm());
        return "signInForm";
    }

    /**
     * 로그인
     * 직접적으로 로그인을 진행하는 매핑입니다.
     * @param email : 로그인 이메일
     * @param password : 로그인 비밀번호
     * @return : 로그인 후 메인페이지
     */
    @PostMapping("/signIn")
    public String signIn(HttpServletRequest request,
                         @RequestParam("email") String email,
                         @RequestParam("password") String password,
                         Model model){
        HttpSession session = request.getSession();
        Long memberId = memberService.signIn(email, password);
        Member findMember = memberService.findOne(memberId);
        model.addAttribute("memberId", memberId);
        model.addAttribute("nickname", findMember.getNickname());
        session.setAttribute("memberId", findMember.getId());
        session.setAttribute("SIGNIN", "TRUE");
        return "Main2";
    }

    /**
     * 로그아웃
     * 로그인을 한 상태에서 로그아웃을 누르면 로그아웃을 진행합니다.
     * 현재 session 에 저장된 memberId 값과 SIGNIN 값을 지우고
     * 세션을 초기화합니다.
     * @return : 로그인 전 메인페이지
     */
    @GetMapping("/signOut")
    public String signOut(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("memberId");
        session.removeAttribute("SIGNIN");
        session.invalidate();
        return "Main";
    }

    @GetMapping("/main2")
    public String toSignMain(){
        return "Main2";
    }

    @GetMapping("/main")
    public String toUnsignMain(){

        return "Main";
    }
}
