package knu.networksecuritylab.appserver.web.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ThesisImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "thesis_image_id")
    private Long id;
    private String imageName;
    private Long imageSize;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thesis_id")
    private Thesis thesis;

    @Builder
    public ThesisImage(String imageName, Long imageSize) {
        this.imageName = imageName;
        this.imageSize = imageSize;
    }

    public void setThesis(Thesis thesis) {
        this.thesis = thesis;
    }
}
