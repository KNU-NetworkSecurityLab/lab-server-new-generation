package knu.networksecuritylab.appserver.web.repository;

import knu.networksecuritylab.appserver.web.entity.Thesis;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ThesisRepository extends JpaRepository<Thesis, Long> {

    @Query("SELECT t FROM Thesis t ORDER BY t.year DESC, t.month DESC")
    List<Thesis> findRecent5Theses(Pageable pageable);

    @Query("SELECT t FROM Thesis t ORDER BY t.year DESC, t.month DESC")
    List<Thesis> findThesesOrOrderByDesc();
}
