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

    public MemberDto(String email, String password, String phoneNumber, String nickName){
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.nickName = nickName;
    }
}
