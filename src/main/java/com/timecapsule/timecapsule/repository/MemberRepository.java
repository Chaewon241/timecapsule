package com.timecapsule.timecapsule.repository;

import com.timecapsule.timecapsule.domain.GroupMember;
import com.timecapsule.timecapsule.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByNickname(String nickname){
        return em.createQuery("select m from Member m where m.nickname = :nickname", Member.class)
                .setParameter("nickname", nickname)
                .getResultList();
    }

    public List<Member> findByEmail(String email){
        return em.createQuery("select m from Member m where m.email = :email", Member.class)
                .setParameter("email", email)
                .getResultList();
    }

    public List<Member> findByPhoneNumber(String phoneNumber){
        return em.createQuery("select m from Member m where m.phoneNumber = :phoneNumber", Member.class)
                .setParameter("phoneNumber", phoneNumber)
                .getResultList();
    }

    public void removeMember(Member member){
        List<GroupMember> groupMembers = member.getGroupMembers();
        for (GroupMember groupMember : groupMembers) {
            em.remove(groupMember);
        }
        em.remove(member);
        em.flush();
    }
}
