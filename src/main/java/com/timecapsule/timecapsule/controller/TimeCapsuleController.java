package com.timecapsule.timecapsule.controller;

import com.timecapsule.timecapsule.MultimediaStore;
import com.timecapsule.timecapsule.domain.Group;
import com.timecapsule.timecapsule.domain.Member;
import com.timecapsule.timecapsule.domain.TimeCapsule;
import com.timecapsule.timecapsule.service.GroupService;
import com.timecapsule.timecapsule.service.MemberService;
import com.timecapsule.timecapsule.service.MultimediaService;
import com.timecapsule.timecapsule.service.TimeCapsuleService;
import java.net.MalformedURLException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class TimeCapsuleController {
    
    private final TimeCapsuleService timeCapsuleService;
    private final GroupService groupService;
    private final MemberService memberService;
    private final MultimediaService multimediaService;
    private final MultimediaStore multimediaStore;
    
    /**
     * t
     * 타임캡슐 생성 폼을 넘겨주는 컨트롤러
     *
     * @return makenewtimecapsule
     */
    @GetMapping("/timeCapsule/new")
    public String createForm(HttpServletRequest request, Model model) {
        
        HttpSession session = request.getSession();
        Long groupId = Long.valueOf(String.valueOf(session.getAttribute("groupId")));
        
        Group findGroup = groupService.findOne(groupId);
        Member findMember = memberService.findOne(
            Long.valueOf(String.valueOf(session.getAttribute("memberId"))));
        
        model.addAttribute("groupName", findGroup.getGroupName());
        model.addAttribute("memberName", findMember.getNickname());
        return "makenewtimecapsule";
    }
    
    /**
     * 타임캡슐 생성 폼의 결과를 받아 타임캡슐을 생성한 후 메인 페이지로 이동시키는 컨트롤러
     */
    @PostMapping("/timeCapsule/new")
    public String create(HttpServletRequest request, @RequestParam("title") String title,
                         @RequestParam("text") String text,
                         @RequestParam("multipartFiles") List<MultipartFile> multipartFiles,
                         Model model)
        throws IOException {
        
        HttpSession session = request.getSession();
        Member findMember = memberService.findOne(
            Long.valueOf(String.valueOf(session.getAttribute("memberId"))));
        
        Long timeCapsuleId = timeCapsuleService.createNewTimeCapsule(
            Long.valueOf(String.valueOf(session.getAttribute("groupId"))), findMember.getId(),
            title, text);
        
        session.removeAttribute("groupId");
        
        if (!multipartFiles.get(0).getOriginalFilename().isEmpty()) {
            multimediaService.createNewMultimedias(timeCapsuleId, multipartFiles);
        }
        
        model.addAttribute("nickname", findMember.getNickname());
        
        return "redirect:/Main2";
    }
    
    /**
     * 해당하는 group의 모든 타임캡슐을 가져오는 컨트롤러
     *
     * @return timecapsuleList
     */
    @GetMapping("/timeCapsule/group/{group_id}")
    public String groupTimeCapsule(@PathVariable("group_id") Long groupId,
                                   HttpServletRequest request, Model model) {
        
        HttpSession session = request.getSession();
        session.setAttribute("groupId", groupId);
        
        List<TimeCapsule> timeCapsules = timeCapsuleService.findTimeCapsuleByGroup(groupId);
        LocalDateTime openDate = groupService.findOne(groupId).getOpenDate();
        String findDate = openDate.toString();
        model.addAttribute("timeCapsules", timeCapsules);
        model.addAttribute("findDate", findDate);
        
        return "timecapsuleList";
    }
    
    /**
     * 해당하는 타임캡슐을 가져오는 컨트롤러
     *
     * @return opentimecapsule
     */
    @GetMapping("/timeCapsule/{time_capsule_id}")
    public String timeCapsule(@PathVariable("time_capsule_id") Long timeCapsuleId, Model model) {
        
        TimeCapsule timeCapsule = timeCapsuleService.findOne(timeCapsuleId);
        
        if (timeCapsule.getGroup().getOpenDate().compareTo(LocalDateTime.now()) <= 0) {
            model.addAttribute("timeCapsule", timeCapsule);
        } else {
            model.addAttribute("msg", "접근 권한이 없습니다.");
        }
        
        return "opentimecapsule";
    }
    
    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws
        MalformedURLException {
        return new UrlResource("file:" + multimediaStore.getFullPath(filename));
    }
}