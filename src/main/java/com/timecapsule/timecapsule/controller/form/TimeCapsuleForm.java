package com.timecapsule.timecapsule.controller.form;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class TimeCapsuleForm {

    private Long timeCapsuleId;

    private Long groupId;

    private Long memberId;

    @NotEmpty(message = "title은 Null일 수 없습니다.")
    @Size(min = 1, max = 32, message = "title은 1 ~ 32자 사이여야 합니다.")
    private String title;

    private String text;

    private List<MultipartFile> multipartFiles;

    private LocalDateTime saveDate;
}
