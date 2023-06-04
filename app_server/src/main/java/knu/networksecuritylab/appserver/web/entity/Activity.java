package knu.networksecuritylab.appserver.web.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@ToString
@NoArgsConstructor
@Getter
@Data
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id")
    private Long id;

    private String title;
    private String content; // 클릭 시 확대해서 보여줄 내용
    private Integer year;
    private Integer month;
    private Integer day;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private WebImage image;

    @Builder
    public Activity(String title, String content, Integer year, Integer month, Integer day, WebImage image) {
        this.title = title;
        this.content = content;
        this.year = year;
        this.month = month;
        this.day = day;
        this.image = image;
    }

    public void setImage(WebImage image) {
        this.image = image;
    }

    public String getDateString() {
        return String.format("%04d-%02d-%02d", year, month, day);
    }
}
