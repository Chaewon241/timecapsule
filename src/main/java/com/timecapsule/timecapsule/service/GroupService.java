package com.timecapsule.timecapsule.service;

import com.timecapsule.timecapsule.domain.Group;
import com.timecapsule.timecapsule.domain.GroupMember;
import com.timecapsule.timecapsule.domain.Member;
import com.timecapsule.timecapsule.repository.GroupRepository;
import com.timecapsule.timecapsule.repository.GroupSearch;
import com.timecapsule.timecapsule.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GroupService {

    private final GroupRepository groupRepository;
    private final MemberRepository memberRepository;

    /**
     * 그룹 생성
     * memberID, 그룹 이름, 열람날짜를 받아서 그룹을 생성하게 됨
     * 그룹을 생성하면서 GroupMember 객체를 만들고, 그룹에 있는 GroupMemberList 에 들어감
     * GroupMember 는 생성될 때 현재 만드는 그룹으로 Group 속성이 설정되어서 생성됨
     * 그룹을 만드는 사람이 leader 이므로, GroupMember 개체에 isGroupLeader 속성을 true 로 설정
     * isGroupLeader 속성의 default = false
     */
    @Transactional
    public void createGroup(Long memberId, String groupName, LocalDateTime openDate, String password) {
        Member leader = memberRepository.findOne(memberId);
        GroupMember groupMember = new GroupMember(leader);
        Group group = Group.createGroup(groupName, openDate, groupMember, password);
        groupRepository.save(group);
    }

    /**
     * 그룹 검색
     * GroupSearch 에 검색할 조건과 내용이 들어가게 됨
     * 그룹리더의 이름으로 검색 or 그룹명으로 검색
     */
    public List<Group> findGroups(GroupSearch groupSearch) {
        return groupRepository.findAllByCriteria(groupSearch);
    }

    /**
     * 그룹 삭제
     * groupId 를 받아서 그룹을 찾고, 해당 그룹을 삭제하는 기능
     * group 개체의 cancel 메소드를 실행시켜서 진행됨
     * group.cancel 은 실행되면 해당 그룹안에 있는 GroupMember 개체를 삭제하게 된다.
     * 흐름 : group.cancel -> groupMember.remove
     */
    public void cancelGroup(Long groupId) {
        Group group = groupRepository.findOne(groupId);
        groupRepository.removeGroup(group);
    }

    /**
     * openDate 수정
     * groupId를 받아서 그룹을 찾고, 해당 그룹의 openDate 를 수정하는 기능
     * group 개체의 updateOpenDate 를 사용해서 진행됨
     * updateOpenDate 를 진행할 때 isChangedOpenDate 를 통해 수정이 가능한지 확인
     * isChangedOpenDate 의 default 값은 false 이고, 한번 수정이 진행되었다면 true 로 설정된다.
     * 만약 true 이면 수정이 불가능하다. (날짜 수정은 한번만 된다는 흐름)
     */
    public void updateOpenDate(Long groupId, LocalDateTime newOpenDate) {
        Group group = groupRepository.findOne(groupId);
        if (!group.updateOpenDate(newOpenDate)) {
            throw new IllegalStateException("열람날짜 수정은 한번만 가능합니다.");
        }
    }

    /**
     * 그룹 ID 로 찾기
     * 그룹을 조회해서 해당 그룹을 누르고 들어갔을 때, 그룹의 정보를 보여주기 위한 기능
     * groupId 를 받아서 해당 ID 로 조회함
     */
    public Group findOne(Long groupId) {
        Group group = groupRepository.findOne(groupId);
        return group;
    }

    /**
     * 그룹에 가입하기
     * 그룹 ID 와 멤버 ID 를 가지고 멤버와 그룹을 찾아오고,
     * 그룹의 패스워드와 입력된 패스워드를 비교해서 가입을 승인하게 됩니다.
     */
    public void joinGroup(Long groupId, Long memberId, String password) {
        Group group = groupRepository.findOne(groupId);
        Member member = memberRepository.findOne(memberId);

        if (group.getPassword().equals(password)) {
            //가입 승인
            GroupMember groupMember = new GroupMember(member);
            group.addGroupMember(groupMember);
        } else {
            //가입 실패
            throw new IllegalStateException("그룹 가입 실패");
        }

    }
}
