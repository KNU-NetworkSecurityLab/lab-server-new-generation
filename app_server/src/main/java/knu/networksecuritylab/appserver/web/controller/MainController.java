package knu.networksecuritylab.appserver.web.controller;

import knu.networksecuritylab.appserver.app.controller.user.dto.SignInRequestDto;
import knu.networksecuritylab.appserver.app.entity.user.User;
import knu.networksecuritylab.appserver.app.service.user.UserService;
import knu.networksecuritylab.appserver.web.service.ActivityService;
import knu.networksecuritylab.appserver.web.service.ThesisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.security.Principal;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {

    private final ActivityService activityService;
    private final ThesisService thesisService;
    private final UserService userService;

    @GetMapping(value = "/")
    public String index(Model model, @AuthenticationPrincipal User user) {
//        if (principal != null) {
//            model.addAttribute("user", userService.getUserInfo(principal.getName()));
//            log.debug("user debug log: {}", userService.getUserInfo(principal.getName()).getName());
//        } else {
//            model.addAttribute("user", null);
//            log.debug("user debug log: null");
//        }

        if (user != null) {
            model.addAttribute("user", user);
            log.debug("user debug log: {}", user.getName());
        } else {
            model.addAttribute("user", null);
            log.debug("user debug log: null");
        }

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


    @GetMapping("/login")
    public String adminLogin() {
        return "adminLogin";
    }

}
