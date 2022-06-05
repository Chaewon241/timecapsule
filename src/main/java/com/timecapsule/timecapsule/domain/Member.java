package com.timecapsule.timecapsule.domain;

import com.timecapsule.timecapsule.domain.dto.MemberDto;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String email;
    private String password;
    private String nickname;
    private String phoneNumber;

    @OneToMany(mappedBy = "member")
    private List<GroupMember> groupMembers;

    protected Member(){}

    public Member(String email, String password, String phoneNumber, String nickname){
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.nickname = nickname;
    }

    public void updateMemberInfo(MemberDto dto){
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.phoneNumber = dto.getPhoneNumber();
        this.nickname = dto.getNickName();
    }
}
