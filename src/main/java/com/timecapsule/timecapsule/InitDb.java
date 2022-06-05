package com.timecapsule.timecapsule;

import com.timecapsule.timecapsule.domain.Group;
import com.timecapsule.timecapsule.domain.GroupMember;
import com.timecapsule.timecapsule.domain.Member;
import com.timecapsule.timecapsule.domain.TimeCapsule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{
        private final EntityManager em;

        public void dbInit1(){
            Member member1 = new Member("test@test.com", "1234",
                    "01011112222", "테스트닉네임");
            em.persist(member1);

            Member member2 = new Member("test@gmail.com", "1234",
                    "01033334444", "테스트닉네임2");
            em.persist(member2);

            Member member3 = new Member("ggeggrgg@naver.com", "1234",
                    "01055556666", "깨아르");
            em.persist(member3);

            GroupMember groupMember1 = new GroupMember();
            groupMember1.setMember(member1);
            em.persist(groupMember1);

            GroupMember groupMember2 = new GroupMember();
            groupMember2.setMember(member2);
            em.persist(groupMember2);

            LocalDateTime time1 = LocalDateTime.now();
            Group group1 = Group.createGroup("테스트그룹", time1, groupMember1, "1234");
            em.persist(group1);

            LocalDateTime time2 = LocalDateTime.now();
            Group group2 = Group.createGroup("테스트그룹2", time2, groupMember2, "1234");
            em.persist(group2);
    
            TimeCapsule timeCapsule1 = TimeCapsule.createTimeCapsule(group1, member1, "make time Capsule test", "make time Capsule test");
            em.persist(timeCapsule1);
    
            TimeCapsule timeCapsule2 = TimeCapsule.createTimeCapsule(group1, member1, "make time Capsule test 2", "make time Capsule test 2");
            em.persist(timeCapsule2);
            
        }
    }
}
