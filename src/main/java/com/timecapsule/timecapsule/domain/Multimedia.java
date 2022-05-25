package com.timecapsule.timecapsule.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Multimedia {

    @Id
    @GeneratedValue
    @Column(name = "multimedia_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "time_capsule_id")
    private TimeCapsule timeCapsule;

    private String originalFileName;

    private String storeFileName;

    // Multimedia 생성자
    public Multimedia(TimeCapsule timeCapsule, String originalFileName, String storeFileName) {
        this.timeCapsule = timeCapsule;
        this.originalFileName = originalFileName;
        this.storeFileName = storeFileName;
    }

    public void setTimeCapsule(TimeCapsule timeCapsule) {
        this.timeCapsule = timeCapsule;
    }
}