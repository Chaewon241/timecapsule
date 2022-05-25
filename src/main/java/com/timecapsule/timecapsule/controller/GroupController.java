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
import java.util.List;

@Controller
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;
    private final MemberService memberService;

    //Get 매핑으로 group/new 로 넘어오면 그룹을 새로 생성하는 폼을 보여줍니다.
    //그룹 생성을 위한 createGroupForm 페이지 필요
    @GetMapping("/group/new")
    public String createForm(Model model){
        model.addAttribute("GroupForm", new GroupForm());
        return "group/createGroupForm";
    }

    //Post 매핑으로 group/new 로 넘어오면 폼의 데이터로 그룹을 생성함
    //BindingResult 에 에러가 있을 시 createMemberForm 으로 다시 넘김
    //session 에 있는 memberId 를 이용해서 현재 로그인하고 있는 회원을 그룹 leader 로 설정함
    @PostMapping("/group/new")
    public String create(@Valid GroupForm form, BindingResult result,
                         HttpServletRequest request){
        if(result.hasErrors()){
            return "group/createGroupForm";
        }
        HttpSession session = request.getSession();
        Long memberId = Long.valueOf(String.valueOf(session.getAttribute("memberId")));
        groupService.createGroup(memberId, form.getGroupName(), form.getOpenDate());
        return "redirect:/";
    }

    //Get 매핑으로 group/{id} 로 넘어오면 해당 그룹의 정보 데이터를 넘겨줌
    //GroupMember 를 탐색해서 leader 를 찾고, leader 의 닉네임도 같이 넘겨준다.
    //그룹의 정보를 보여주기 위한 info 페이지 필요
    @GetMapping("/group/{id}")
    public String findOne(@PathVariable("id") Long groupId, Model model){
        Group group = groupService.findOne(groupId);

        List<GroupMember> gms = group.getGroupMembers();
        Member leader = null;
        for (GroupMember gm : gms) {
            if(gm.getIsGroupLeader()){
                leader = gm.getMember();
            }
        }

        model.addAttribute(group.getGroupName());
        model.addAttribute(group.getOpenDate());
        model.addAttribute(leader.getNickname());

        return "group/info";
    }

    //Post 매핑으로 group/{id} 로 넘어오면 해당 그룹의 열람 날짜를 수정함
    //form 으로 넘어온 LocalDateTime 인 newOpenDate 로 열람 날짜를 변경함
    @PostMapping("/group/{id}")
    public String updateOpenDate(@PathVariable("id") Long groupId,
                                 @RequestParam("openDate") LocalDateTime newOpenDate){
        groupService.updateOpenDate(groupId, newOpenDate);
        return "redirect:/group/"+groupId;
    }

    //Get 매핑으로 group 으로 넘어오면 그룹 리스트를 보여줌
    //groupSearch 로 검색 데이터를 전달받는데, 초기에는 조건이 없기 떄문에 모든 그룹이 다 나옴
    //그룹 리스트를 표시하기 위한 group/groupList 페이지 필요
    @GetMapping("/group")
    public String groupList(@ModelAttribute("groupSearch")GroupSearch groupSearch, Model model){
        List<Group> groups = groupService.findGroups(groupSearch);
        model.addAttribute("groups", groups);

        return "group/groupList";
    }

    //Post 매핑으로 group/{id}/cancel 로 넘어오면 그룹을 삭제함
    //넘어온 {id} 값을 사용해서 group 을 찾아서 group 을 삭제함
    @PostMapping("/group/{id}/cancel")
    public String cancelGroup(@PathVariable("id") Long groupId){
        groupService.cancelGroup(groupId);
        return "redirect:/group";
    }
}
