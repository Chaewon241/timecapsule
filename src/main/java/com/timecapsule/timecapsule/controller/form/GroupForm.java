package com.timecapsule.timecapsule.controller.form;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GroupForm {
    private String groupName;
    private LocalDateTime openDate;
}
