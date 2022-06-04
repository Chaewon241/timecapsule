package com.timecapsule.timecapsule;

import com.timecapsule.timecapsule.domain.Group;
import com.timecapsule.timecapsule.domain.GroupMember;
import com.timecapsule.timecapsule.domain.Member;
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
            Member member = new Member("test@test.com", "1234",
                    "01011112222", "테스트");
            em.persist(member);

            GroupMember groupMember = new GroupMember(member);
            em.persist(groupMember);

            LocalDateTime time = LocalDateTime.now();
            Group group = Group.createGroup("TestGroup", time, groupMember, "1234");
            em.persist(group);
        }
    }
}
