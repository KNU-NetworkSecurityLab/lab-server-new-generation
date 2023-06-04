package knu.networksecuritylab.appserver.web.repository;

import knu.networksecuritylab.appserver.web.entity.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThesisRepository extends JpaRepository<Thesis, Long> {

    List<Thesis> findTop5ByOrderByIdDesc();
}
