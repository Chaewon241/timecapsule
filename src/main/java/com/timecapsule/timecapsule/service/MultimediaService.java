package com.timecapsule.timecapsule.service;

import com.timecapsule.timecapsule.MultimediaStore;
import com.timecapsule.timecapsule.domain.Group;
import com.timecapsule.timecapsule.domain.Multimedia;
import com.timecapsule.timecapsule.domain.TimeCapsule;
import com.timecapsule.timecapsule.repository.MultimediaRepository;
import com.timecapsule.timecapsule.repository.TimeCapsuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MultimediaService {

    private final MultimediaRepository multimediaRepository;
    private final TimeCapsuleRepository timeCapsuleRepository;
    private final MultimediaStore multimediaStore;

    /**
     * 멀티미디어 파일들 생성
     */
    @Transactional
    public int createNewMultimedias(Long timeCapsuleId, List<MultipartFile> multipartFiles) throws IOException {

        // 해당되는 타임캡슐 찾기
        TimeCapsule timeCapsule = timeCapsuleRepository.findOne(timeCapsuleId);

        for (MultipartFile multipartFile : multipartFiles) {
            Multimedia multimedia = multimediaStore.storeFile(timeCapsule, multipartFile); // multipartFile 저장
            multimediaRepository.save(multimedia); // DB에 multimedia 저장
        }

        return multipartFiles.size();
    }
}
