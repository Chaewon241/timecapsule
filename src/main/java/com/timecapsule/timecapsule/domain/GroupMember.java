package com.timecapsule.timecapsule.domain;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
public class GroupMember {

    @Id @GeneratedValue
    @Column(name="groupmember_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    private Member member;

    @ManyToOne(fetch = LAZY)
    private Group group;
}
