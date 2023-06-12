package knu.networksecuritylab.appserver.web.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ThesisContributor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "thesis_contributor_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contributor_id")
    private Contributor contributor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thesis_id")
    private Thesis thesis;

    @Builder
    public ThesisContributor(Contributor contributor, Thesis thesis) {
        this.contributor = contributor;
        this.thesis = thesis;
    }

    public static ThesisContributor from(Contributor contributor, Thesis thesis) {
        return ThesisContributor.builder()
                .contributor(contributor)
                .thesis(thesis)
                .build();
    }
}
