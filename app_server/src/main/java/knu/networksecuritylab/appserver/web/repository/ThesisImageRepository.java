package knu.networksecuritylab.appserver.web.repository;

import knu.networksecuritylab.appserver.web.entity.WebImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThesisImageRepository extends JpaRepository<WebImage, Long> {
}
