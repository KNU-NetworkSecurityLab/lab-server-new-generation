package knu.networksecuritylab.appserver.web.controller;

import knu.networksecuritylab.appserver.app.controller.user.dto.SignInRequestDto;
import knu.networksecuritylab.appserver.app.service.user.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/admin")
public class AdminController {

    private final UserService userService;

    @GetMapping
    public String admin() {
        return "adminDashBoard";
    }

}
