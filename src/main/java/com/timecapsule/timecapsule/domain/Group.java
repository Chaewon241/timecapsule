package com.timecapsule.timecapsule.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
public class Group {

    @Id @GeneratedValue
    @Column(name = "Group_id")
    private Long id;

    private String openDate;

    private Boolean isChangedOpenDate;

    @OneToOne(fetch = LAZY)
    private Member groupLeader;

    @OneToMany
    private List<GroupMember> groupMembers;


//    private List<TimeCapules> timeCapules;
}
