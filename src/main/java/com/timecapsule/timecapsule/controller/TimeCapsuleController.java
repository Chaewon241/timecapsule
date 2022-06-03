package com.timecapsule.timecapsule.controller;

import com.timecapsule.timecapsule.controller.form.TimeCapsuleForm;
import com.timecapsule.timecapsule.domain.Group;
import com.timecapsule.timecapsule.domain.TimeCapsule;
import com.timecapsule.timecapsule.service.GroupService;
import com.timecapsule.timecapsule.service.MemberService;
import com.timecapsule.timecapsule.service.MultimediaService;
import com.timecapsule.timecapsule.service.TimeCapsuleService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class TimeCapsuleController {
    
    private final TimeCapsuleService timeCapsuleService;
    private final GroupService groupService;
    private final MultimediaService multimediaService;
    
    /**
     * 타임캡슐 생성 폼을 넘겨주는 컨트롤러
     * @param model
     * @return makenewtimecapsule
     */
    @GetMapping("/timeCapsule/new")
    public String createForm(Model model) {
        
        model.addAttribute("timeCapsuleForm", new TimeCapsuleForm());
        return "makenewtimecapsule";
    }
    
    /**
     * 타임캡슐 생성 폼의 결과를 받아 타임캡슐을 생성한 후 메인 페이지로 redirect 시키는 컨트롤러
     * @param form
     * @return Main
     */
    @PostMapping("/timeCapsule/new")
    public String create(@Valid TimeCapsuleForm form) throws IOException {
        
        Long timeCapsuleId = timeCapsuleService.createNewTimeCapsule(form.getGroupId(),
                                                                     form.getTitle(),
                                                                     form.getText());
        multimediaService.createNewMultimedias(timeCapsuleId, form.getMultipartFiles());
        
        return "redirect:/";
    }
    
    /**
     * 해당하는 group의 모든 타임캡슐을 가져오는 컨트롤러
     * @param groupId
     * @param model
     * @return TimecapsuleList
     */
    @GetMapping("/timeCapsule/group/{group_id}")
    public String groupTimeCapsule(@PathVariable("group_id") Long groupId, Model model) {
        
        Group group = groupService.findOne(groupId);
        
        List<TimeCapsule> timeCapsules = timeCapsuleService.findTimeCapsuleByGroup(groupId);
        model.addAttribute("timeCapsules", timeCapsules);
        
        return "TimecapsuleList";
    }
    
    /**
     * 해당하는 타임캡슐을 가져오는 컨트롤러
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
        
        return "time-capsule-view";
    }
}
