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
    public List<Member> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        sort(members);
        return members;
    }

    public List<Member> studentList() {
        List<Member> reader = memberRepository.findMembersByMemberState(MemberState.STUDENT_READER);
        List<Member> normal = memberRepository.findMembersByMemberState(MemberState.STUDENT_NORMAL);
        reader.addAll(normal);
        return reader;
    }

    @Override
    public Member getProfessor() {
        return memberRepository.findMemberByMemberState(MemberState.PROFESSOR);
    }

    @Override
    @Transactional
    public Long addMember(MemberRequestDto memberRequestDto) {
        return memberRepository.save(Member.from(memberRequestDto)).getId();
    }

    @Override
    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow();
    }

    @Transactional
    @Override
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateMember(Long id, Member member) {
        Member findMember = memberRepository.findById(id).orElseThrow();
        findMember.update(member);
        memberRepository.save(findMember);
    }

    private void sort(List<Member> members) {
        members.sort((o1, o2) -> {
            if (o1.getMemberState().equals(o2.getMemberState())) {
                return o1.getId().compareTo(o2.getId());
            } else {
                return o1.getMemberState().compareTo(o2.getMemberState());
            }
        });
    }
}
