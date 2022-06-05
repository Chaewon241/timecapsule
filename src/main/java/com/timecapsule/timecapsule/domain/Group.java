package com.timecapsule.timecapsule.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<GroupMember> groupMembers = new ArrayList<>();

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<TimeCapsule> timeCapsules = new ArrayList<>();

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

    /**
     * Group 리더 찾기
     * 그룹의 리더 이름을 가져와서 반환합니다.
     * @return : 리더 이름(String)
     */
    public String getLeader(){
        for (GroupMember groupMember : groupMembers) {
            if(groupMember.getIsGroupLeader() == Boolean.TRUE){
                return groupMember.getMember().getNickname();
            }
        }
        throw new IllegalStateException("그룹 리더가 없습니다.");
    }
}
