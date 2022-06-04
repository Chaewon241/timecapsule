package com.timecapsule.timecapsule.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Table(name = "groups")
public class Group {

    @Id @GeneratedValue
    @Column(name = "group_id")
    private Long id;

    private String groupName;

    private LocalDateTime openDate;

    private Boolean isChangedOpenDate = false;

    private String password;

    @OneToMany(mappedBy = "group")
    private List<GroupMember> groupMembers;

    @OneToMany(mappedBy = "group")
    private List<TimeCapsule> timeCapsules;

    protected Group(){}

    public Group(String groupName, LocalDateTime openDate, String password) {
        this.groupName = groupName;
        this.openDate = openDate;
        this.password = password;
    }

    public boolean updateOpenDate(LocalDateTime newOpenDate){
        if(!isChangedOpenDate){
            this.openDate = newOpenDate;
            return true;
        }
        else{
            return false;
        }
    }

    //==연관관계 메서드==//
    public void addGroupMember(GroupMember groupMember){
        groupMembers.add(groupMember);
        groupMember.setGroup(this);
    }

    //===생성 메서드==//
    public static Group createGroup(String groupName, LocalDateTime openDate,
                                    GroupMember groupMember, String password) {
        Group group = new Group(groupName, openDate, password);
        group.addGroupMember(groupMember);
        groupMember.updateIsGroupLeader();
        return group;
    }

    /**
     * Group 삭제
     * 그룹에 속한 groupMember 들을 지운다.
     * group.cancel -> groupMember.remove
     */
    public void cancel(){
        for (GroupMember groupMember : groupMembers) {
            groupMember.remove();
        }
    }
}
