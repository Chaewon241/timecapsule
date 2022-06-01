package com.timecapsule.timecapsule.repository;

import com.timecapsule.timecapsule.domain.Alarm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AlarmRepository {

    private final EntityManager em;

    public void save(Alarm alarm){
        em.persist(alarm);
    }

    public Alarm findOne(Long id){
        return em.find(Alarm.class, id);
    }

    public List<Alarm> findAll(){
        return em.createQuery("select a from Alarm a", Alarm.class)
                .getResultList();
    }

    public void removeAlarm(Alarm alarm){
        em.remove(alarm);
    }

}
