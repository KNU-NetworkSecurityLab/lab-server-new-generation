package knu.networksecuritylab.appserver.web.controller;

import knu.networksecuritylab.appserver.web.entity.Member;
import knu.networksecuritylab.appserver.web.entity.Thesis;
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
@RequestMapping(value = "/thesis")
public class ThesisController {

    private final ThesisService thesisService;
    private final MemberService memberService;

    @Value("${image.uploadPath}")
    private String uploadPath;

    @ModelAttribute("members")
    public List<Member> members() {
        return memberService.getAllMembers();
    }

    @ModelAttribute("imagePath")
    public String imagePath() {
        return uploadPath;
    }

    @GetMapping
    public String publication(Model model) {
        model.addAttribute("theses", thesisService.findAllTheses());
        return "thesis";
    }

    @PostMapping(value = "/add")
    public String memberAdd(
            @ModelAttribute Thesis thesis,
            @RequestParam("memberIds") List<Long> memberIds,
            @RequestParam("thesisImage") MultipartFile thesisImage
    ) throws Exception {
        thesisService.addThesis(thesis, memberIds, thesisImage);
        return "redirect:/thesis";
    }

    @GetMapping(value = "/add")
    public String thesisAddForm(Model model) {
        model.addAttribute("thesis", new Thesis());
        return "thesisRegister";
    }

    @GetMapping("/image/{fileName:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveImage(@PathVariable String fileName) {
        Resource file = new FileSystemResource("/Users/uknow/lab_service_images/" + fileName);

        if (file.exists()) {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").body(file);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
