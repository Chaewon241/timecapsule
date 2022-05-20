package com.timecapsule.timecapsule.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {

    private String email;
    private String nickName;
    private String phoneNumber;
    private String password;

    public MemberDto(String email, String nickName, String phoneNumber, String password){
        this.email = email;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}
