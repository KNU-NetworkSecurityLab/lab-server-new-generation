package knu.networksecuritylab.appserver.service.github;

import knu.networksecuritylab.appserver.controller.github.dto.LanguageRateResponseDto;
import knu.networksecuritylab.appserver.controller.github.dto.RepositoryListResponseDto;

import java.util.List;

public interface GithubService {

    void refreshReposLanguageInfo();

    void updateLanguageRate();

    List<LanguageRateResponseDto> organizationLanguagesRate();

    List<RepositoryListResponseDto> organizationRepositoryList();
}
