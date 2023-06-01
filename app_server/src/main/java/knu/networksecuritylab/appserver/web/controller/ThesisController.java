package knu.networksecuritylab.appserver.web.controller;

import knu.networksecuritylab.appserver.web.entity.Member;
import knu.networksecuritylab.appserver.web.entity.Thesis;
import knu.networksecuritylab.appserver.web.service.MemberService;
import knu.networksecuritylab.appserver.web.service.ThesisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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
    public String memberAdd(@ModelAttribute Thesis thesis, @RequestParam("memberIds") List<Long> memberIds) {
        thesisService.addThesis(thesis, memberIds);
        return "redirect:/thesis";
    }

    @GetMapping(value = "/add")
    public String thesisAddForm(Model model) {
        model.addAttribute("thesis", new Thesis());
        return "thesisRegister";
    }
}
