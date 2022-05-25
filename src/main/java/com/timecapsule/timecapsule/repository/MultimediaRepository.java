package com.timecapsule.timecapsule.repository;

import com.timecapsule.timecapsule.domain.Multimedia;
import com.timecapsule.timecapsule.domain.TimeCapsule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class MultimediaRepository {
    private final EntityManager em;

    public void save(Multimedia multimedia) {
        em.persist(multimedia);
    }
}
