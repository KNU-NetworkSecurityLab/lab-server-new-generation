package knu.networksecuritylab.appserver.web.controller.admin;

import knu.networksecuritylab.appserver.web.entity.Member;
import knu.networksecuritylab.appserver.web.entity.Thesis;
import knu.networksecuritylab.appserver.web.entity.dto.ThesisRequestDto;
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
@RequestMapping(value = "/admin/thesis")
public class AdminThesisController {

    private final ThesisService thesisService;
    private final MemberService memberService;

    @Value("${image.uploadPath}")
    private String uploadPath;

    @ModelAttribute("members")
    public List<Member> members() {
        return memberService.getAllMembers();
    }

    @GetMapping
    public String publication(Model model) {
        model.addAttribute("theses", thesisService.findAllTheses());
        return "admin/thesis/adminThesis";
    }

    @PostMapping(value = "/add")
    public String memberAdd(
            @ModelAttribute ThesisRequestDto thesisRequestDto,
            @RequestParam("webImage") MultipartFile thesisImage
    ) throws Exception {
        thesisService.addThesis(thesisRequestDto, thesisImage);
        return "redirect:/admin/thesis";
    }

    @GetMapping(value = "/add")
    public String thesisAddForm(Model model) {
        model.addAttribute("thesis", new ThesisRequestDto());
        return "admin/thesis/adminThesisRegister";
    }

    @GetMapping("/image/{fileName:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveImage(@PathVariable String fileName) {
        Resource file = new FileSystemResource(uploadPath + '/' + fileName);

        if (file.exists()) {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").body(file);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/delete/{id}")
    public String thesisDelete(@PathVariable Long id) {
        thesisService.deleteThesis(id);
        return "redirect:/admin/thesis";
    }
}
