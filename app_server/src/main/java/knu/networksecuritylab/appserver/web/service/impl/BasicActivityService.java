package knu.networksecuritylab.appserver.web.service.impl;

import knu.networksecuritylab.appserver.web.entity.Activity;
import knu.networksecuritylab.appserver.web.entity.WebImage;
import knu.networksecuritylab.appserver.web.entity.dto.ActivityRequestDto;
import knu.networksecuritylab.appserver.web.repository.ActivityRepository;
import knu.networksecuritylab.appserver.web.service.ActivityService;
import knu.networksecuritylab.appserver.web.service.file.WebImageFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BasicActivityService implements ActivityService {

    private final ActivityRepository activityRepository;
    private final WebImageFileService webImageFileService;

    @Override
    public List<Activity> getAllActivities() {

        return activityRepository.findActivitiesOrOrderByDayDesc();
    }

    @Transactional
    @Override
    public void addActivity(ActivityRequestDto activityRequestDto, MultipartFile multipartFile) throws Exception {
        WebImage webImage = webImageFileService.multipartFileStoreAndConvertToImage(multipartFile);
        Activity activity = Activity.from(activityRequestDto, webImage);
        activityRepository.save(activity);
    }

    @Override
    public List<Activity> getRecent6Activities() {
        return activityRepository.findTop6ByOrderByYearDesc(PageRequest.of(0, 6));
    }


    @Transactional
    @Override
    public void deleteActivity(Long id) {
        Activity activity = activityRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 활동이 없습니다. id=" + id));
        activityRepository.delete(activity);
    }
}
