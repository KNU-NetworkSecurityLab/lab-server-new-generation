package knu.networksecuritylab.appserver.web.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    private String contentSummary; // 마우스 오버 시 보여줄 내용
    private String content; // 클릭 시 확대해서 보여줄 내용
    private LocalDate date;
}
