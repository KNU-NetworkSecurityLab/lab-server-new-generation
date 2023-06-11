package knu.networksecuritylab.appserver.web.controller;

import knu.networksecuritylab.appserver.web.entity.Member;
import knu.networksecuritylab.appserver.web.entity.MemberState;
import knu.networksecuritylab.appserver.web.entity.dto.MemberRequestDto;
import knu.networksecuritylab.appserver.web.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/admin")
public class AdminController {

    private final MemberService memberService;

    @ModelAttribute("memberStates")
    public MemberState[] memberStates() {
        return MemberState.values();
    }

    @GetMapping
    public String admin() {
        return "admin/adminDashBoard";
    }

    @GetMapping("/member")
    public String member(Model model) {
        model.addAttribute("members", memberService.getAllMembers());
        return "admin/adminMember";
    }

    @GetMapping("/member/edit/{id}")
    public String memberEdit(Model model, @PathVariable Long id) {
        model.addAttribute("member", memberService.getMemberById(id));
        return "admin/adminMemberEdit";
    }

    @PostMapping("/member/edit/{id}")
    public String memberEdit(@PathVariable Long id, Member member) {
        memberService.updateMember(id, member);
        return "redirect:/admin/member";
    }

    @GetMapping("/member/delete/{id}")
    public String memberDelete(@PathVariable Long id) {
        memberService.deleteMember(id);
        return "redirect:/admin/member";
    }

    @GetMapping("/member/add")
    public String memberAddForm(Model model) {
        model.addAttribute("member", new MemberRequestDto());
        return "admin/adminMemberRegister";
    }

    @PostMapping("/member/add")
    public String memberAdd(MemberRequestDto memberRequestDto) {
        memberService.addMember(memberRequestDto);
        return "redirect:/admin/member";
    }

}
