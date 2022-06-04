package com.timecapsule.timecapsule.controller;

import com.timecapsule.timecapsule.controller.form.GroupForm;
import com.timecapsule.timecapsule.domain.Group;
import com.timecapsule.timecapsule.domain.GroupMember;
import com.timecapsule.timecapsule.domain.Member;
import com.timecapsule.timecapsule.repository.GroupSearch;
import com.timecapsule.timecapsule.service.GroupService;
import com.timecapsule.timecapsule.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    /**
     * 그룹을 생성할 페이지로 넘어가게 만들어줄 매핑입니다.
     * @return : 그룹 생성 페이지로 이동
     */
    @GetMapping("/group/new")
    public String createForm(Model model) {
        model.addAttribute("GroupForm", new GroupForm());
        return "createGroupForm";
    }

    /**
     * 그룹을 생성하는 매핑입니다.
     * 그룹의 이름과 열람날짜를 받아서 해당 그룹을 생성합니다.
     * @param groupName : 생성할 그룹의 이름
     * @param openDate : 생성할 그룹의 열람 날짜
     * @return : 로그인한 메인 페이지로 이동
     */
    @PostMapping("/group/new")
    public String create(HttpServletRequest request,
                         @RequestParam("groupName") String groupName,
                         @RequestParam("openDate") LocalDateTime openDate,
                         @RequestParam("password") String password) {
        HttpSession session = request.getSession();
        Long memberId = Long.valueOf(String.valueOf(session.getAttribute("memberId")));
        groupService.createGroup(memberId, groupName, openDate, password);
        return "Main2";
    }

    /**
     * 해당 그룹에 대한 정보만을 반환하는 매핑입니다.
     * 한 그룹을 선택했을때 모델에 그룹이름, 열람날짜, 리더닉네임을 넘겨줍니다.
     * @param groupId : 검색할 그룹의 ID 값
     * @Model : groupName, openDate, leaderNickname
     * @return : info 페이지로 이동
     */
    @GetMapping("/group/{id}")
    public String findOne(@PathVariable("id") Long groupId, Model model) {
        Group group = groupService.findOne(groupId);

        List<GroupMember> gms = group.getGroupMembers();
        Member leader = null;
        for (GroupMember gm : gms) {
            if (gm.getIsGroupLeader()) {
                leader = gm.getMember();
            }
        }
        model.addAttribute("groupname", group.getGroupName());
        model.addAttribute("opendate",group.getOpenDate());
        model.addAttribute("leadernickname", leader.getNickname());
        return "info";
    }

    /**
     * 그룹의 열람날짜를 수정하기 위한 매핑입니다.
     * @param groupId : 그룹의 id 값
     * @param newOpenDate : 수정할 열람 날짜(새 날짜)
     * @return : group 페이지로 이동
     */
    @PostMapping("/group/{id}")
    public String updateOpenDate(@PathVariable("id") Long groupId,
                                 @RequestParam("openDate") LocalDateTime newOpenDate) {
        groupService.updateOpenDate(groupId, newOpenDate);
        return "groupList";
    }

    /**
     * 기본적인 그룹 리스트를 띄우는 매핑입니다.
     * 들어오면 모든 그룹을 검색해서 값을 반환합니다.
     * @Model : groups
     * @return : groupList 페이지로 이동
     */
    @GetMapping("/group")
    public String groupList(Model model) {
        GroupSearch groupSearch = new GroupSearch();
        List<Group> groups = groupService.findGroups(groupSearch);
        List<Member> leaders = new ArrayList<>();

        for (Group group : groups) {
            List<GroupMember> gms = group.getGroupMembers();
            for (GroupMember gm : gms) {
                if (gm.getIsGroupLeader()) {
                    leaders.add(gm.getMember());
                }
            }
        }
        model.addAttribute("groups", groups);
        model.addAttribute("leaders", leaders);
        return "groupList";
    }

    /**
     * 검색한 그룹 정보를 반환하는 매핑입니다.
     * 검색된 결과를 그룹 리스트 형태로 model 에 담아줍니다.
     * @param option : 선택된 옵션(그룹명 or 리더닉네임 중 선택)
     * @param text : 선택된 옵션에 대한 검색 내용(그룹이름 or 리더닉네임)
     * @model : groups
     * @return : groupList 페이지로 이동
     */
    @PostMapping("/groupList")
    public String groupSearchList(@RequestParam("sel") String option,
                                  @RequestParam("searchText") String text,
                                  Model model) {
        GroupSearch groupSearch = new GroupSearch();
        if (option.equals("groupname")) {
            groupSearch.setGroupName(text);
        } else if (option.equals("leadername")) {
            groupSearch.setLeaderName(text);
        }
        List<Group> groups = groupService.findGroups(groupSearch);
        model.addAttribute("groups", groups);

        return "groupList";
    }

    /**
     * 그룹을 삭제하는 매핑입니다.
     * 그룹을 삭제하고 메인화면으로 이동합니다.
     * @param groupId : 그룹의 id 값
     * @return : 로그인 한 메인페이지로 이동
     */
    @PostMapping("/group/{id}/cancel")
    public String cancelGroup(@PathVariable("id") Long groupId) {
        groupService.cancelGroup(groupId);
        return "Main2";
    }

    /**
     * 그룹에 가입하는 매핑입니다.
     * 가입하고 싶은 그룹과 비밀번호를 받고, 그룹에 가입을 진행합니다.
     * 맴버 ID 값은 로그인한 Session 에 있는 memberId 를 가져옵니다.
     * @param groupId : 그룹의 ID 값(URL 로 넘어옴)
     * @param password : 가입할 그룹의 비밀번호
     * @return : 로그인 한 메인페이지로 이동
     */
    @PostMapping("/group/{id}/join")
    public String joinGroup(HttpServletRequest request,
                            @PathVariable("id") Long groupId,
                            @RequestParam("password") String password){
        HttpSession session = request.getSession();
        Long memberId = Long.valueOf(String.valueOf(session.getAttribute("memberId")));
        groupService.joinGroup(groupId, memberId, password);
        return "Main2";
    }
}
