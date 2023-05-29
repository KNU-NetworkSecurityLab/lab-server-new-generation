package knu.networksecuritylab.appserver.web.service;

import knu.networksecuritylab.appserver.web.entity.Thesis;

import java.util.List;

public interface ThesisService {
    List<Thesis> findAllTheses();
}
