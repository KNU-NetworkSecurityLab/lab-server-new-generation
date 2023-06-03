package knu.networksecuritylab.appserver.web.service;

import knu.networksecuritylab.appserver.web.entity.Thesis;
import knu.networksecuritylab.appserver.web.entity.dto.ThesisRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ThesisService {
    List<Thesis> findAllTheses();

    Long addThesis(Thesis thesis, List<Long> memberIds, MultipartFile thesisImage) throws Exception;
}
