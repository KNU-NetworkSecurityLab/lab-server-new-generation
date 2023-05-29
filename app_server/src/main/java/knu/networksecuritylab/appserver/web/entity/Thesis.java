package knu.networksecuritylab.appserver.web.entity;

import knu.networksecuritylab.appserver.app.entity.book.BookTag;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Thesis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "thesis_id")
    private Long id;

    @NotBlank(message = "이름은 비어있을 수 없습니다.")
    private String title;
    private Integer year;
    private String prize; // 수상내역
    private String journal; // 학회

    @OneToMany(mappedBy = "thesis")
    private List<ThesisAuthor> authors = new ArrayList<>();

    @Builder
    private Thesis(String title, Integer year, String prize, String journal) {
        this.title = title;
        this.year = year;
        this.prize = prize;
        this.journal = journal;
    }
}
