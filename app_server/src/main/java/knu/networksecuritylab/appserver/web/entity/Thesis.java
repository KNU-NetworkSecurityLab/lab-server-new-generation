package knu.networksecuritylab.appserver.web.entity;

import knu.networksecuritylab.appserver.web.entity.dto.ThesisRequestDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@NoArgsConstructor
@Getter
@Data
public class Thesis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "thesis_id")
    private Long id;

    private String title;
    private Integer year;
    private Integer month;
    private String prizeTitle; // 수상 ex> 추계 대학생 논문 경진 대회
    private String prizeGrade; // 수상등급 ex> 최우수상, 금상, 은상, 동상
    private String organization; // ex > 한국정보기술학회, 공주대학교 창업지원센터

    @OneToMany(mappedBy = "thesis", cascade = CascadeType.ALL)
    private final List<ThesisMember> thesisMembers = new ArrayList<>();

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private WebImage image;

    @Builder
    public Thesis(String title, Integer year, Integer month, String prizeTitle, String prizeGrade, String organization, WebImage image) {
        this.title = title;
        this.year = year;
        this.month = month;
        this.prizeTitle = prizeTitle;
        this.prizeGrade = prizeGrade;
        this.organization = organization;
        this.image = image;
    }

    public static Thesis from(ThesisRequestDto thesisRequestDto) {
        return Thesis.builder()
                .title(thesisRequestDto.getTitle())
                .year(thesisRequestDto.getYear())
                .month(thesisRequestDto.getMonth())
                .prizeTitle(thesisRequestDto.getPrizeTitle())
                .prizeGrade(thesisRequestDto.getPrizeGrade())
                .organization(thesisRequestDto.getOrganization())
                .build();
    }

    public void setImage(WebImage image) {
        this.image = image;
    }

    public void addMember(final Member member) {
        this.thesisMembers.add(ThesisMember.from(member, this));
    }

    public String getDate() {
        String month = this.month < 10 ? "0" + this.month : String.valueOf(this.month);
        return this.year + ". " + month;
    }
}
