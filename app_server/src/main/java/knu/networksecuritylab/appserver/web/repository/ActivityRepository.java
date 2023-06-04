package knu.networksecuritylab.appserver.web.repository;

import knu.networksecuritylab.appserver.web.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    @Query("SELECT a FROM Activity a ORDER BY a.year DESC, a.month DESC, a.day DESC")
    List<Activity> findTop6ByOrderByYearDesc();
}
