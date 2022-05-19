package com.timecapsule.timecapsule.domain;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
public class Alarm {

    @Id @GeneratedValue
    @Column(name = "alarm_id")
    private Long id;

    private String alarmDate;

    @OneToOne(fetch = LAZY)
    private Group group;

}
