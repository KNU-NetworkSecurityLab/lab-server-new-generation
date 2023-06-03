package knu.networksecuritylab.appserver.web.service.impl;

import knu.networksecuritylab.appserver.web.entity.Member;
import knu.networksecuritylab.appserver.web.entity.Thesis;
import knu.networksecuritylab.appserver.web.entity.WebImage;
import knu.networksecuritylab.appserver.web.repository.MemberRepository;
import knu.networksecuritylab.appserver.web.repository.ThesisRepository;
import knu.networksecuritylab.appserver.web.service.ThesisService;
import knu.networksecuritylab.appserver.web.service.file.ThesisImageFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BasicThesisService implements ThesisService {

    private final ThesisRepository thesisRepository;
    private final MemberRepository memberRepository;
    private final ThesisImageFileService thesisImageFileService;

    @Override
    public List<Thesis> findAllTheses() {
        return thesisRepository.findAll();
    }

    @Transactional
    @Override
    public Long addThesis(Thesis thesis, List<Long> memberIds, MultipartFile multipartFile) throws Exception {

        memberIds.forEach(memberId -> {
            Member member = memberRepository.findById(memberId).orElseThrow();
            thesis.addMember(member);
        });

        WebImage webImage = thesisImageFileService.multipartFileStoreAndConvertToImage(multipartFile);
        thesis.setImage(webImage);

        return thesisRepository.save(thesis).getId();
    }
}
