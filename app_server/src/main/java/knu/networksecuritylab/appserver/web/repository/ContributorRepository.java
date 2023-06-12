package knu.networksecuritylab.appserver.web.repository;

import knu.networksecuritylab.appserver.web.entity.Contributor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ContributorRepository extends JpaRepository<Contributor, Long> {
    Optional<Contributor> findContributorByName(String name);
}
