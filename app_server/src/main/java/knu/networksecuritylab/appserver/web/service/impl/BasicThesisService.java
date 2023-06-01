package knu.networksecuritylab.appserver.web.service.impl;

import knu.networksecuritylab.appserver.web.entity.Member;
import knu.networksecuritylab.appserver.web.entity.Thesis;
import knu.networksecuritylab.appserver.web.repository.MemberRepository;
import knu.networksecuritylab.appserver.web.repository.ThesisRepository;
import knu.networksecuritylab.appserver.web.service.ThesisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BasicThesisService implements ThesisService {

    private final ThesisRepository thesisRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<Thesis> findAllTheses() {
        return thesisRepository.findAll();
    }

    @Transactional
    @Override
    public Long addThesis(Thesis thesis, List<Long> memberIds) {

        memberIds.forEach(memberId -> {
            Member member = memberRepository.findById(memberId).orElseThrow();
            thesis.addMember(member);
        });

        thesis.getMembers().forEach(thesisMember -> {
            log.info("thesisId: {}, memberId: {}", thesisMember.getThesis().getTitle(), thesisMember.getMember().getMemberName());
        });

        return thesisRepository.save(thesis).getId();
    }
}
