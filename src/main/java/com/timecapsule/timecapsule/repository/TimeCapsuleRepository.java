package com.timecapsule.timecapsule.repository;

import com.timecapsule.timecapsule.domain.TimeCapsule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TimeCapsuleRepository {
    private final EntityManager em;

    public void save(TimeCapsule timeCapsule) {
        em.persist(timeCapsule);
    }

    public List<TimeCapsule> findByGroupId(Long groupId) {
        return em.createQuery("select tc from TimeCapsule tc where tc.group.id = :groupId", TimeCapsule.class)
                .setParameter("groupId", groupId)
                .getResultList();
    }

    public TimeCapsule findOne(Long timeCapsuleId) {
        return em.find(TimeCapsule.class, timeCapsuleId);
    }
}
