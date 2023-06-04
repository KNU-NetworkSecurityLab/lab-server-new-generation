package knu.networksecuritylab.appserver.web.repository;

import knu.networksecuritylab.appserver.web.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
