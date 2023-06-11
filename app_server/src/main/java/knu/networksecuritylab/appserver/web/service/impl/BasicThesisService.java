package knu.networksecuritylab.appserver.web.service.impl;

import knu.networksecuritylab.appserver.web.entity.Member;
import knu.networksecuritylab.appserver.web.entity.Thesis;
import knu.networksecuritylab.appserver.web.entity.ThesisMember;
import knu.networksecuritylab.appserver.web.entity.WebImage;
import knu.networksecuritylab.appserver.web.entity.dto.ThesisRequestDto;
import knu.networksecuritylab.appserver.web.repository.MemberRepository;
import knu.networksecuritylab.appserver.web.repository.ThesisRepository;
import knu.networksecuritylab.appserver.web.service.ContributorService;
import knu.networksecuritylab.appserver.web.service.ThesisService;
import knu.networksecuritylab.appserver.web.service.file.WebImageFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BasicThesisService implements ThesisService {

    private final ThesisRepository thesisRepository;
    private final MemberRepository memberRepository;
    private final WebImageFileService webImageFileService;
    private final ContributorService contributorService;

    @Override
    public List<Thesis> findAllTheses() {
        return thesisRepository.findThesesOrOrderByDesc();
    }

    @Transactional
    @Override
    public Long addThesis(ThesisRequestDto thesisRequestDto, MultipartFile multipartFile) throws Exception {
        WebImage webImage = webImageFileService.multipartFileStoreAndConvertToImage(multipartFile);

        Thesis thesis = Thesis.from(thesisRequestDto, webImage);

        thesisRequestDto.getMembers().forEach(memberId -> {
            Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
            thesis.addMember(member);
        });

        contributorService.contributorArrangement(thesisRequestDto.getContributors())
                .forEach(thesis::addContributor);

        return thesisRepository.save(thesis).getId();
    }

    @Override
    public List<Thesis> getRecent5Theses() {
        return thesisRepository.findRecent5Theses(PageRequest.of(0, 5));
    }

}
