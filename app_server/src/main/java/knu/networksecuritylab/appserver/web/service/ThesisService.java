package knu.networksecuritylab.appserver.web.service;

import knu.networksecuritylab.appserver.web.entity.Thesis;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ThesisService {
    List<Thesis> findAllTheses();

    @Query("SELECT t FROM Thesis t ORDER BY t.year DESC, t.month DESC LIMIT 5")
    List<Thesis> findRecent5Theses();

    Long addThesis(Thesis thesis, List<Long> memberIds, MultipartFile thesisImage) throws Exception;
}
