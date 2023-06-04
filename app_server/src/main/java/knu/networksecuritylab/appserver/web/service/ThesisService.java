package knu.networksecuritylab.appserver.web.service;

import knu.networksecuritylab.appserver.web.entity.Thesis;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ThesisService {
    List<Thesis> findAllTheses();

    List<Thesis> getRecent5Theses();

    Long addThesis(Thesis thesis, List<Long> memberIds, MultipartFile thesisImage) throws Exception;
}
