package com.timecapsule.timecapsule.repository;

import com.timecapsule.timecapsule.domain.Group;
import com.timecapsule.timecapsule.domain.Member;
import com.timecapsule.timecapsule.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class GroupRepository {

    private final EntityManager em;

    public void save(Group group){
        em.persist(group);
    }

    public Group findOne(Long id){
        return em.find(Group.class, id);
    }

    public void removeGroup(Group group){
        group.cancel();
        em.remove(group);
    }

    /**
     * 그룹 전부 찾기
     */
    public List<Group> findAll(){
        return em.createQuery("select g from Group g", Group.class)
                .getResultList();
    }

    /**
     * 조건으로 검색하기
     */
    public List<Group> findAllByCriteria(GroupSearch groupSearch){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Group> cg = cb.createQuery(Group.class);
        Root<Group> g = cg.from(Group.class);
        Join<Object, Object> m = g.join("groupMembers", JoinType.INNER);
        Join<Object, Object> mm = m.join("member", JoinType.INNER);

        List<Predicate> criteria = new ArrayList<>();

        //그룹리더 이름으로 검색
        if(StringUtils.hasText(groupSearch.getLeaderName())){
            Predicate leaderName = cb.and(
                    cb.equal(m.get("isGroupLeader"), true),
                    cb.like(mm.get("nickname"), "%"+groupSearch.getLeaderName()+"%"));
            criteria.add(leaderName);
        }
        //그룹 이름으로 검색
        else if(StringUtils.hasText(groupSearch.getGroupName())){
            Predicate groupName = cb.like(g.get("groupName"), "%" + groupSearch.getGroupName()+"%");
            criteria.add(groupName);
        }

        cg.where(cb.and(criteria.toArray( new Predicate[criteria.size()])));
        TypedQuery<Group> query = em.createQuery(cg).setMaxResults(100);
        return query.getResultList();
    }
}
