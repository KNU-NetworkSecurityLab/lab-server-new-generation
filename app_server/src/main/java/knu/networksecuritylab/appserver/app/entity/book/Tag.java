package knu.networksecuritylab.appserver.app.entity.book;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;
    @NotBlank(message = "책 태그 이름은 비어있을 수 없습니다.")
    @Column(unique = true)
    private String tagName;

    @OneToMany(mappedBy = "tag")
    private final List<BookTag> bookTags = new ArrayList<>();

    @Builder
    private Tag(String tagName) {
        this.tagName = tagName;
    }

    public static Tag from(String tagName) {
        return Tag.builder()
                .tagName(tagName)
                .build();
    }
}
