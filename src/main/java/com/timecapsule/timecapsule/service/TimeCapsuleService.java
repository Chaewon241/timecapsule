package com.timecapsule.timecapsule.service;

import com.timecapsule.timecapsule.domain.Group;
import com.timecapsule.timecapsule.domain.TimeCapsule;
import com.timecapsule.timecapsule.repository.TimeCapsuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TimeCapsuleService {

    private final TimeCapsuleRepository timeCapsuleRepository;
    private final GroupRepository groupRepository;

    /**
     * 타임캡슐 생성
     * @param groupId
     * @param title
     * @param text
     * @return timeCapsule의 id
     */
    @Transactional
    public Long createNewTimeCapsule(Long groupId, String title, String text) {

        // 타임캡슐 검증
        verifyTimeCapsule(title);

        // 타임캡슐 생성
        Group group = groupRepository.findOne(groupId);
        TimeCapsule timeCapsule = TimeCapsule.createTimeCapsule(group, title, text);

        // 타임캡슐 저장
        timeCapsuleRepository.save(timeCapsule);

        return timeCapsule.getId();
    }

    /**
     * 타임캡슐 유효성 검증(제목 유무)
     * @param title
     */
    private void verifyTimeCapsule(String title) {
        if (title.isEmpty()) {
            throw new IllegalStateException("title이 비어있습니다.");
        }
    }

    /**
     * 그룹별 타임캡슐 조회
     *
     * @param groupId
     * @return List<TimeCapsule>
     */
    public List<TimeCapsule> findTimeCapsuleByGroup(Long groupId) {
        return timeCapsuleRepository.findByGroupId(groupId);
    }

    /**
     * 타임캡슐 조회
     *
     * @param timeCapsuleId
     * @return TimeCapsule
     */
    public TimeCapsule findOne(Long timeCapsuleId) {
        return timeCapsuleRepository.findOne(timeCapsuleId);
    }
}
