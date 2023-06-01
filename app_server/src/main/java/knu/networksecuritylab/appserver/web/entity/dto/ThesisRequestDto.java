package knu.networksecuritylab.appserver.web.entity.dto;

import knu.networksecuritylab.appserver.web.entity.ThesisAuthor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder
public class ThesisRequestDto {
    private String title; // 제목, ex> 위치기반 코로나 19 방역알림 프로젝트
    private Integer year;
    private String prize; // 수상내역 ex> 추계 대학생 논문 경진 대회 은상
    private String journal; // 학회 ex> 한국정보기술학회

    @OneToMany(mappedBy = "thesis")
    private List<ThesisAuthor> authors;
}
