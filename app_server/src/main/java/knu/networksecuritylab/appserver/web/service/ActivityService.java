package knu.networksecuritylab.appserver.web.service;

import knu.networksecuritylab.appserver.web.entity.Activity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ActivityService {
    List<Activity> getAllActivities();

    void addActivity(Activity activity, MultipartFile multipartFile) throws Exception;

    List<Activity> getRecent6Activities();
}
