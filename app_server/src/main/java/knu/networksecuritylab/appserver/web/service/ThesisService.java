package knu.networksecuritylab.appserver.web.service;

import knu.networksecuritylab.appserver.web.entity.Thesis;
import knu.networksecuritylab.appserver.web.entity.dto.ThesisRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ThesisService {
    List<Thesis> findAllTheses();

    List<Thesis> getRecent5Theses();

    Long addThesis(ThesisRequestDto thesisRequestDto, MultipartFile thesisImage) throws Exception;

    Thesis getThesis(Long id);

    void deleteThesis(Long id);
}
