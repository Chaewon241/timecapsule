package com.timecapsule.timecapsule.controller;

import com.timecapsule.timecapsule.controller.form.TimeCapsuleForm;
import com.timecapsule.timecapsule.domain.TimeCapsule;
import com.timecapsule.timecapsule.service.GroupService;
import com.timecapsule.timecapsule.service.MemberService;
import com.timecapsule.timecapsule.service.MultimediaService;
import com.timecapsule.timecapsule.service.TimeCapsuleService;
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
    private final MemberService memberService;
    private final MultimediaService multimediaService;

    @GetMapping("/time_capsule/new")
    public String createForm(Model model) {
        model.addAttribute("timeCapsuleForm", new TimeCapsuleForm());
        return "time-capsule-form";
    }

    @PostMapping("/time_capsule/new")
    public String create(@Valid TimeCapsuleForm form) throws IOException {

        Long timeCapsuleId = timeCapsuleService.createNewTimeCapsule(form.getGroupId(), form.getTitle(), form.getText());
        multimediaService.createNewMultimedias(timeCapsuleId, form.getMultipartFiles());

        return "redirect:/";
    }

    @GetMapping("/time_capsule/{time_capsule_id}")
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
