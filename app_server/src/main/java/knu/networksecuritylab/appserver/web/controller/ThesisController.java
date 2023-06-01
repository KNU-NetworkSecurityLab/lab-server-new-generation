package knu.networksecuritylab.appserver.web.controller;

import knu.networksecuritylab.appserver.web.entity.Member;
import knu.networksecuritylab.appserver.web.entity.MemberState;
import knu.networksecuritylab.appserver.web.entity.dto.MemberRequestDto;
import knu.networksecuritylab.appserver.web.entity.dto.ThesisRequestDto;
import knu.networksecuritylab.appserver.web.service.MemberService;
import knu.networksecuritylab.appserver.web.service.ThesisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/thesis")
public class ThesisController {

    private final ThesisService thesisService;
    private final MemberService memberService;


    @ModelAttribute("members")
    public List<Member> members() {
        return memberService.getAllMembers();
    }

    @GetMapping
    public String publication(Model model) {
        model.addAttribute("theses", thesisService.findAllTheses());
        return "thesis";
    }

    @PostMapping(value = "/add")
    public String memberAdd(ThesisRequestDto thesisRequestDto) {
        thesisService.addThesis(thesisRequestDto);
        return "redirect:/thesis";
    }

    @GetMapping(value = "/add")
    public String thesisAddForm() {
        return "thesisRegister";
    }
}
