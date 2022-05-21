package com.timecapsule.timecapsule.controller.form;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class MemberForm {

    @NotEmpty(message = "이메일은 필수입니다.")
    private String email;

    @NotEmpty(message = "닉네임은 필수입니다.")
    private String nickname;

    @NotEmpty(message = "닉네임은 필수 입니다.")
    private String phoneNumber;

    @NotEmpty(message = "닉네임은 필수 입니다.")
    private String password;
}
