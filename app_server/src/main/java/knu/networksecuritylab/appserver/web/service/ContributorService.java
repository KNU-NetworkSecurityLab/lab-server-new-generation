package knu.networksecuritylab.appserver.web.service;

import knu.networksecuritylab.appserver.web.entity.Contributor;

import java.util.List;

public interface ContributorService {
    List<Contributor> contributorArrangement(List<String> contributorList);
}
