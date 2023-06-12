package knu.networksecuritylab.appserver.web.controller.admin;

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
@RequestMapping(value = "/admin/member")
public class AdminMemberController {

    private final MemberService memberService;

    @ModelAttribute("memberStates")
    public MemberState[] memberStates() {
        return MemberState.values();
    }

    @GetMapping
    public String member(Model model) {
        model.addAttribute("members", memberService.getAllMembers());
        return "admin/member/adminMember";
    }

    @GetMapping("/edit/{id}")
    public String memberEdit(Model model, @PathVariable Long id) {
        model.addAttribute("member", memberService.getMemberById(id));
        return "admin/member/adminMemberEdit";
    }

    @PostMapping("/edit/{id}")
    public String memberEdit(@PathVariable Long id, Member member) {
        memberService.updateMember(id, member);
        return "redirect:/admin/member";
    }

    @GetMapping("/delete/{id}")
    public String memberDelete(@PathVariable Long id) {
        memberService.deleteMember(id);
        return "redirect:/admin/member";
    }

    @GetMapping("/add")
    public String memberAddForm(Model model) {
        model.addAttribute("member", new MemberRequestDto());
        return "admin/member/adminMemberRegister";
    }

    @PostMapping("/add")
    public String memberAdd(MemberRequestDto memberRequestDto) {
        memberService.addMember(memberRequestDto);
        return "redirect:/admin/member";
    }
}
