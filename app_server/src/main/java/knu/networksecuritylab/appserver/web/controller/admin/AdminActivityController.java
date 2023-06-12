package knu.networksecuritylab.appserver.web.controller.admin;

import knu.networksecuritylab.appserver.web.entity.Activity;
import knu.networksecuritylab.appserver.web.entity.Member;
import knu.networksecuritylab.appserver.web.entity.dto.ActivityRequestDto;
import knu.networksecuritylab.appserver.web.entity.dto.ThesisRequestDto;
import knu.networksecuritylab.appserver.web.service.ActivityService;
import knu.networksecuritylab.appserver.web.service.MemberService;
import knu.networksecuritylab.appserver.web.service.ThesisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/admin/activity")
public class AdminActivityController {

    private final ActivityService activityService;

    @Value("${image.uploadPath}")
    private String uploadPath;

    @GetMapping
    public String activity(Model model) {
        model.addAttribute("activities", activityService.getAllActivities());
        return "admin/photo/adminPhoto";
    }

    @GetMapping("/add")
    public String addPhotoForm(Model model) {
        model.addAttribute("activity", new ActivityRequestDto());
        return "admin/photo/adminPhotoRegister";
    }

    @PostMapping("/add")
    public String memberAdd(
            @ModelAttribute ActivityRequestDto activityRequestDto,
            @RequestParam("webImage") MultipartFile thesisImage
    ) throws Exception {
        activityService.addActivity(activityRequestDto, thesisImage);
        return "redirect:/admin/activity";
    }


    @GetMapping("/image/{fileName:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveImage(@PathVariable String fileName) {
        Resource file = new FileSystemResource(uploadPath + '/' + fileName);

        if (file.exists()) {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").body(file);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
