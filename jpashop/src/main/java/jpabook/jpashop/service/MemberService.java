package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
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
    @Transactional  //readOnly = false가 기본값임
    public Long join(Member member) {
        validateDuplicateMember(member);   //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {     //중복되었을 때 실행됨
        List<Member> findMembers = memberRepository.findByName(member.getName());
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//        System.out.println(member);
//        findMembers.add(member);
//        findMembers.add(member);
//        System.out.println(findMembers);
        if (!findMembers.isEmpty()) {
//            System.out.println("이미 존재");
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 조회
     */
    //전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //단건 회원 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

}
