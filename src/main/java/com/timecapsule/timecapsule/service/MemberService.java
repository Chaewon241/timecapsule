package com.timecapsule.timecapsule.service;

import com.timecapsule.timecapsule.domain.Member;
import com.timecapsule.timecapsule.domain.dto.MemberDto;
import com.timecapsule.timecapsule.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Member member){
        validateDuplicateNickname(member);
        validateDuplicateEmail(member);
        validateDuplicatePhoneNumber(member);
        memberRepository.save(member);
        return member.getId();
    }

    //중복 닉네임 검증
    private void validateDuplicateNickname(Member member){
        List<Member> findMembers = memberRepository.findByNickname(member.getNickname());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");
        }
    }

    //중복 이메일 검증
    private void validateDuplicateEmail(Member member){
        List<Member> findMembers = memberRepository.findByEmail(member.getEmail());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }
    }

    //중복 휴대폰번호 검증
    private void validateDuplicatePhoneNumber(Member member){
        List<Member> findMembers = memberRepository.findByPhoneNumber(member.getPhoneNumber());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 휴대폰번호입니다.");
        }
    }

    /**
     * 로그인 기능
     */
    //로그인
    public Long signIn(String email, String password){
        List<Member> findMembers = memberRepository.findByEmail(email);
        if(!findMembers.isEmpty()){
            Member targetMember = findMembers.get(0);
            if (targetMember.getPassword().equals(password)) {
                //로그인 성공
                return targetMember.getId();
            }
        }
        //로그인 실패
        throw new IllegalStateException("로그인 실패");
    }

    /**
     * 회원 조회
     */
    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //회원 개별 조회
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

    /**
     * 회원 정보
     */
    //회원 정보 수정
    @Transactional
    public Member updateMemberInfo(Long id, MemberDto updateDTO){
        Member member = memberRepository.findOne(id);
        if(!member.equals(null)){
            member.updateMemberInfo(updateDTO);
            return member;
        }
        throw new IllegalStateException("해당 회원이 없음");
    }

    //회원 탈퇴
    public void removeMember(Long memberId){
        Member member = memberRepository.findOne(memberId);
        if(!member.equals(null)){
            memberRepository.removeMember(member);
            return;
        }
        throw new IllegalStateException("해당 회원이 없음");
    }

}

