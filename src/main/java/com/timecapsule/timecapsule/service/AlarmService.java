package com.timecapsule.timecapsule.service;

import com.timecapsule.timecapsule.domain.Group;
import com.timecapsule.timecapsule.domain.GroupMember;
import com.timecapsule.timecapsule.domain.Member;
import com.timecapsule.timecapsule.domain.TimeCapsule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlarmService {

    private final MessageService messageService;

    private final MailSendService mailSendService;

    /**
     * 알람 전송
     * group 을 전달받아서 그룹안에 있는 멤버들을 조회하고, 각 멤버들의 번호, 이메일을 가져와서
     * 해당 이메일과 번호로 알람 메시지를 전달합니다.
     */
    public void sendAlarm(Group group){
        List<GroupMember> groupMembers = group.getGroupMembers();
        for (GroupMember groupMember : groupMembers) {
            Member member = groupMember.getMember();
            messageService.sendMessage(member.getPhoneNumber());
            mailSendService.sendAlarm(member.getEmail());
        }
    }
}
