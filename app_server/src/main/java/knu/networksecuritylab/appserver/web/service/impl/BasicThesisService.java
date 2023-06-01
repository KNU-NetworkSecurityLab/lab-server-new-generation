package knu.networksecuritylab.appserver.web.service.impl;

import knu.networksecuritylab.appserver.web.entity.Thesis;
import knu.networksecuritylab.appserver.web.entity.dto.ThesisRequestDto;
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

    @Override
    public List<Thesis> findAllTheses() {
        return thesisRepository.findAll();
    }

    @Transactional
    @Override
    public Long addThesis(ThesisRequestDto thesisRequestDto) {
        return thesisRepository.save(thesisRepository.save(Thesis.from(thesisRequestDto))).getId();
    }
}
