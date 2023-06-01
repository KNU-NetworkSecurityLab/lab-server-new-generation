package knu.networksecuritylab.appserver.web.controller;

import knu.networksecuritylab.appserver.web.entity.MemberState;
import knu.networksecuritylab.appserver.web.entity.dto.MemberRequestDto;
import knu.networksecuritylab.appserver.web.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/member")
public class MemberController {
    private final MemberService memberService;

    @ModelAttribute("memberStates")
    public MemberState[] memberStates() {
        return MemberState.values();
    }

    @GetMapping
    public String member(Model model) {
        model.addAttribute("professor", memberService.getProfessor());
        model.addAttribute("members", memberService.studentList());
        return "member";
    }

    @PostMapping(value = "/add")
    public String memberAdd(MemberRequestDto memberRequestDto) {
        memberService.addMember(memberRequestDto);
        return "redirect:/member";
    }

    @GetMapping(value = "/add")
    public String memberAddForm() {
        return "memberRegister";
    }
}
