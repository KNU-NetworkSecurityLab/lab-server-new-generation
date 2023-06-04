package knu.networksecuritylab.appserver.web.service.impl;

import knu.networksecuritylab.appserver.web.entity.Activity;
import knu.networksecuritylab.appserver.web.entity.WebImage;
import knu.networksecuritylab.appserver.web.repository.ActivityRepository;
import knu.networksecuritylab.appserver.web.service.ActivityService;
import knu.networksecuritylab.appserver.web.service.file.WebImageFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        return activityRepository.findAll();
    }

    @Transactional
    @Override
    public void addActivity(Activity activity, MultipartFile multipartFile) throws Exception {
        WebImage webImage = webImageFileService.multipartFileStoreAndConvertToImage(multipartFile);
        activity.setImage(webImage);
        activityRepository.save(activity);
    }
}
