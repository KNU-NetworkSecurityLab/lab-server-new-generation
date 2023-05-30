package knu.networksecuritylab.appserver.web.service.impl;

import knu.networksecuritylab.appserver.web.entity.Member;
import knu.networksecuritylab.appserver.web.entity.MemberState;
import knu.networksecuritylab.appserver.web.entity.dto.MemberRequestDto;
import knu.networksecuritylab.appserver.web.repository.MemberRepository;
import knu.networksecuritylab.appserver.web.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BasicMemberService implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public List<Member> studentList() {
        return memberRepository.findMemberByMemberStateNot(MemberState.PROFESSOR);
    }

    @Override
    public Member getProfessor() {
        return memberRepository.findMemberByMemberState(MemberState.PROFESSOR);
    }

    @Override
    @Transactional
    public Long addMember(MemberRequestDto memberRequestDto) {
        Member member = Member.from(memberRequestDto);
        return memberRepository.save(member).getId();
    }
}
