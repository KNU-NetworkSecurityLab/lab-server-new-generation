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
    private List<ThesisMember> thesisMembers = new ArrayList<>();

    @OneToMany(mappedBy = "thesis", cascade = CascadeType.ALL)
    private List<ThesisContributor> thesisContributors = new ArrayList<>();

    @OneToOne(cascade = {CascadeType.ALL})
    private WebImage image;

    @Builder
    public Thesis(
            String title,
            Integer year,
            Integer month,
            String prizeTitle,
            String prizeGrade,
            String organization,
            WebImage image
    ) {
        this.title = title;
        this.year = year;
        this.month = month;
        this.prizeTitle = prizeTitle;
        this.prizeGrade = prizeGrade;
        this.organization = organization;
        this.image = image;
    }

    public static Thesis from(
            ThesisRequestDto thesisRequestDto,
            WebImage image
    ) {
        return Thesis.builder()
                .title(thesisRequestDto.getTitle())
                .year(thesisRequestDto.getYear())
                .month(thesisRequestDto.getMonth())
                .prizeTitle(thesisRequestDto.getPrizeTitle())
                .prizeGrade(thesisRequestDto.getPrizeGrade())
                .organization(thesisRequestDto.getOrganization())
                .image(image)
                .build();
    }

    public String getDate() {
        String month = this.month < 10 ? "0" + this.month : String.valueOf(this.month);
        return this.year + ". " + month;
    }

    public void addMember(Member member) {
        this.thesisMembers.add(ThesisMember.from(member, this));
    }

    public void addContributor(Contributor contributor) {
        this.thesisContributors.add(ThesisContributor.from(contributor, this));
    }

    public String getContributors() {
        StringBuilder sb = new StringBuilder();
        for (ThesisContributor thesisContributor : this.thesisContributors) {
            sb.append(thesisContributor.getContributor().getName()).append("\n");
        }
        return sb.toString();
    }

    public String getMembers() {
        StringBuilder sb = new StringBuilder();
        for (ThesisMember thesisMember : this.thesisMembers) {
            sb.append(thesisMember.getMember().getMemberName()).append("\n");
        }
        return sb.toString();
    }

    public void update(Thesis thesis, WebImage webImage) {
        this.title = thesis.title;
        this.year = thesis.year;
        this.month = thesis.month;
        this.prizeTitle = thesis.prizeTitle;
        this.prizeGrade = thesis.prizeGrade;
        this.organization = thesis.organization;
        this.image = webImage;
    }
}
