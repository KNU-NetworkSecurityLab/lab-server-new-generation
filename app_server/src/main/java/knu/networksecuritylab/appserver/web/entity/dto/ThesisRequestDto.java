package knu.networksecuritylab.appserver.web.entity.dto;

import knu.networksecuritylab.appserver.web.entity.Member;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ThesisRequestDto {
    private String title; // 제목, ex> 위치기반 코로나 19 방역알림 프로젝트
    private Integer year;
    private Integer month;
    private String prizeTitle; // 수상 ex> 추계 대학생 논문 경진 대회
    private String prizeGrade; // 수상등급 ex> 최우수상, 금상, 은상, 동상
    private String organization; // ex> 한국정보기술학회, 공주대학교 창업지원센터

    private List<Long> members = new ArrayList<>();
}
