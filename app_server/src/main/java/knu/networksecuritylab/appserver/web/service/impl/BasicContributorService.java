package knu.networksecuritylab.appserver.web.service.impl;

import knu.networksecuritylab.appserver.web.entity.Contributor;
import knu.networksecuritylab.appserver.web.repository.ContributorRepository;
import knu.networksecuritylab.appserver.web.service.ContributorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BasicContributorService implements ContributorService {

    private final ContributorRepository contributorRepository;

    @Transactional
    @Override
    public List<Contributor> contributorArrangement(final List<String> contributorList) {
        List<Contributor> contributors = new ArrayList<>();

        contributorList.forEach(contributorName -> {
            Contributor contributor = contributorRepository.findContributorByName(contributorName)
                    .orElseGet(() -> contributorRepository.save(Contributor.from(contributorName)));

            if (!contributors.contains(contributor)) {
                contributors.add(contributor);
            }
        });

        return contributors;
    }
}
