package com.timecapsule.timecapsule.controller.form;

import com.timecapsule.timecapsule.domain.Group;
import com.timecapsule.timecapsule.domain.GroupMember;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@NotEmpty
public class MemberInfoForm {

    private String email;
    private String password;
    private String nickname;
    private String phoneNumber;

    public MemberInfoForm(String email, String password, String nickname,
                          String phoneNumber) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
    }
}
