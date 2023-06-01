package knu.networksecuritylab.appserver.web.controller;

import knu.networksecuritylab.appserver.web.service.ThesisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class MainController {

    private final ThesisService thesisService;

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/notice")
    public String notice() {
        return "notice";
    }
}