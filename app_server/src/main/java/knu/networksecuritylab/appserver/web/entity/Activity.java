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
    private Integer year;
    private Integer month;
    private Integer day;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private WebImage image;

    @Builder
    public Activity(String title, Integer year, Integer month, Integer day, WebImage image) {
        this.title = title;
        this.year = year;
        this.month = month;
        this.day = day;
        this.image = image;
    }

    public void setImage(WebImage image) {
        this.image = image;
    }

    public String getDateString() {
        StringBuilder sb = new StringBuilder();
        sb.append(year);

        if (month == null || month == 0) return sb.toString();
        sb.append(". ");
        if (month < 10) sb.append("0");
        sb.append(month);

        if (day == null || day == 0) return sb.toString();
        sb.append(". ");
        if (day < 10) sb.append("0");
        sb.append(day);

        return sb.toString();
    }
}
