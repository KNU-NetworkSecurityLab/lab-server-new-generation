package knu.networksecuritylab.appserver.web.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WebImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;
    private String imageName;
    private Long imageSize;

    @Builder
    public WebImage(String imageName, Long imageSize) {
        this.imageName = imageName;
        this.imageSize = imageSize;
    }

}
