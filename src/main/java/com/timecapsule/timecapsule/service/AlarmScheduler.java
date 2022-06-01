package com.timecapsule.timecapsule.service;

import com.timecapsule.timecapsule.domain.Group;
import com.timecapsule.timecapsule.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@EnableScheduling
@Configuration
@RequiredArgsConstructor
public class AlarmScheduler {

    private final AlarmService alarmService;
    private final GroupRepository groupRepository;

    /**
     * 매일 날짜 체크
     * 매일 0시 1분에 날짜를 체크하도록 작동합니다.
     * 해당 시간에 되면 스프링 Scheduled 에 등록되어 있는 DailyDateCompare 가 작동해서
     * 전체 그룹 목록을 가져와서 그룹들의 openDate 와 현재 날짜를 비교하여
     * 알람을 전송합니다.
     */
    //매일 0시 1분에 날짜 체크
    @Scheduled(cron = "0 1 0 * * * ")
    public void DailyCheck(){
        List<Group> groups = groupRepository.findAll();
        LocalDateTime today = LocalDateTime.now();
        for (Group group : groups) {
            CompareDate(group, today);
        }
    }

    private void CompareDate(Group group, LocalDateTime today){
        LocalDateTime time = group.getOpenDate();
        LocalDate timeDate = LocalDate.from(time);
        LocalDate todayDate = LocalDate.from(today);
        if(timeDate.isEqual(todayDate)){
            alarmService.sendAlarm(group);
        }
    }
}
