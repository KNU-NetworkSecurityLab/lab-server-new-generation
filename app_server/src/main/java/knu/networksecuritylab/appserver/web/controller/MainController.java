package knu.networksecuritylab.appserver.web.controller;

import knu.networksecuritylab.appserver.web.service.ActivityService;
import knu.networksecuritylab.appserver.web.service.ThesisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {

    private final ActivityService activityService;
    private final ThesisService thesisService;

    @GetMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("theses", thesisService.getRecent5Theses());
        model.addAttribute("activities", activityService.getRecent6Activities());
        return "index";
    }

    @GetMapping(value = "/notice")
    public String notice() {
        return "notice";
    }

    @GetMapping(value = "/contact")
    public String contact() {
        return "contact";
    }
}