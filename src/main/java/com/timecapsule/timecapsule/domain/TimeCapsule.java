package com.timecapsule.timecapsule.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TimeCapsule {

    @Id
    @GeneratedValue
    @Column(name = "time_capsule_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    private String title;

    private String text;

    @OneToMany(mappedBy = "time_capsule", cascade = CascadeType.ALL)
    private List<Multimedia> files;

    private LocalDateTime saveDate;

    //== 타임캡슐 생성 메서드 ==//
    public static TimeCapsule createTimeCapsule(Group group, String title, String text) {
        TimeCapsule timeCapsule = new TimeCapsule(group, title, text);
        return timeCapsule;
    }

//    //== 연관관계 메서드 ==//
//    // 양방향에 세팅하는 것을 한 코드에 넣어 원자적으로 만든다.
//    private void addMultimedia(Multimedia file) {
//        files.add(file);
//        file.setTimeCapsule(this);
//    }

    //== 타입캡슐 생성자 ==//
    public TimeCapsule(Group group, String title, String text) {
        this.group = group;
        this.title = title;
        this.text = text;
        this.saveDate = LocalDateTime.now();
    }
}
