package com.timecapsule.timecapsule.domain;

import lombok.Getter;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
public class GroupMember {

    @Id @GeneratedValue
    @Column(name="group_member_id")
    private Long id;

    private Boolean isGroupLeader = false;

    @ManyToOne(fetch = LAZY)
    private Member member;

    @ManyToOne(fetch = LAZY)
    private Group group;

    protected GroupMember(){}

    public GroupMember(Member member) {
        this.member = member;
    }

    public void setGroup(Group group){
        this.group = group;
    }

    public void updateIsGroupLeader(){
        this.isGroupLeader = true;
    }

    /**
     * 멤버의 GroupMember 리스트에서 GroupMember 를 지운다.
     * group.cancel 실행 후 -> groupMember.remove 실행
     */
    public void remove(){
        List<GroupMember> groups = getMember().getGroupMembers();
        for (GroupMember groupMember : groups) {
            if(groupMember.getId() == id){
                groups.remove(this);
            }
        }
    }
}
