package com.timecapsule.timecapsule.controller.form;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class SignInForm {

    @NotEmpty(message = "이메일을 입력해주세요.")
    private String email;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password;
}
