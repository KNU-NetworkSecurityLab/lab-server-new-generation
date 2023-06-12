package knu.networksecuritylab.appserver.web.entity.dto;

import knu.networksecuritylab.appserver.web.entity.WebImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ActivityRequestDto {
    private String title; // 제목, ex> 위치기반 코로나 19 방역알림 프로젝트
    private Integer year;
    private Integer month;
    private Integer day;
}