package com.timecapsule.timecapsule.controller;

import com.timecapsule.timecapsule.controller.form.TimeCapsuleForm;
import com.timecapsule.timecapsule.domain.Group;
import com.timecapsule.timecapsule.domain.Member;
import com.timecapsule.timecapsule.domain.Multimedia;
import com.timecapsule.timecapsule.domain.TimeCapsule;
import com.timecapsule.timecapsule.service.GroupService;
import com.timecapsule.timecapsule.service.MemberService;
import com.timecapsule.timecapsule.service.MultimediaService;
import com.timecapsule.timecapsule.service.TimeCapsuleService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Text;

@Controller
@RequiredArgsConstructor
public class TimeCapsuleController {
    
    private final TimeCapsuleService timeCapsuleService;
    private final GroupService groupService;
    private final MemberService memberService;
    private final MultimediaService multimediaService;
    
    /**t
     * 타임캡슐 생성 폼을 넘겨주는 컨트롤러
     *
     * @param model
     * @return makenewtimecapsule
     */
    @GetMapping("/timeCapsule/new")
    public String createForm(HttpServletRequest request, Model model) {
        
        HttpSession session = request.getSession();
        Long groupId = Long.valueOf(String.valueOf(session.getAttribute("groupId")));
        
        Group findGroup = groupService.findOne(groupId);
        Member findMember = memberService.findOne(Long.valueOf(String.valueOf(session.getAttribute("memberId"))));
        
        model.addAttribute("groupName", findGroup.getGroupName());
        model.addAttribute("memberName", findMember.getNickname());
        return "makenewtimecapsule";
    }
    
    /**
     * 타임캡슐 생성 폼의 결과를 받아 타임캡슐을 생성한 후 메인 페이지로 redirect 시키는 컨트롤러
     *
     * @param form
     * @return Main
     */
    @PostMapping("/timeCapsule/new")
    public String create(HttpServletRequest request, @RequestParam("title") String title, @RequestParam("text") String text,
                         @RequestParam("multipartFiles")List<MultipartFile> multipartFiles)
        throws IOException {
        
        HttpSession session = request.getSession();
        
        Long timeCapsuleId = timeCapsuleService.createNewTimeCapsule(Long.valueOf(String.valueOf(session.getAttribute("groupId"))),
                                                                     Long.valueOf(String.valueOf(session.getAttribute("memberId"))),
                                                                     title,
                                                                     text);
    
        session.removeAttribute("groupId");
    
        multimediaService.createNewMultimedias(timeCapsuleId, multipartFiles);
        
        return "redirect:/";
    }
    
    /**
     * 해당하는 group의 모든 타임캡슐을 가져오는 컨트롤러
     *
     * @param groupId
     * @param model
     * @return TimecapsuleList
     */
    @GetMapping("/timeCapsule/group/{group_id}")
    public String groupTimeCapsule(@PathVariable("group_id") Long groupId, HttpServletRequest request, Model model) {
        
//        Group group = groupService.findOne(groupId);
        HttpSession session = request.getSession();
        session.setAttribute("groupId", groupId);
    
        List<TimeCapsule> timeCapsules = timeCapsuleService.findTimeCapsuleByGroup(groupId);
        model.addAttribute("timeCapsules", timeCapsules);
        
        return "timecapsuleList";
    }
    
    /**
     * 해당하는 타임캡슐을 가져오는 컨트롤러
     *
     * @param timeCapsuleId
     * @param model
     * @return time-capsule-view
     */
    @GetMapping("/timeCapsule/{time_capsule_id}")
    public String timeCapsule(@PathVariable("time_capsule_id") Long timeCapsuleId, Model model) {
        
        TimeCapsule timeCapsule = timeCapsuleService.findOne(timeCapsuleId);
        
        if (timeCapsule.getGroup().getOpenDate().compareTo(LocalDateTime.now()) >= 0) {
            model.addAttribute("timeCapsule", timeCapsule);
        } else {
            model.addAttribute("msg", "접근 권한이 없습니다.");
        }
        
        return "opentimecapsule";
    }
}
