package knu.networksecuritylab.appserver.repository.github;

import knu.networksecuritylab.appserver.entity.github.RepositoryLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<RepositoryLanguage, Long> {
}
