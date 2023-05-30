package knu.networksecuritylab.appserver.web.entity.dto;

import knu.networksecuritylab.appserver.web.entity.MemberState;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberRequestDto {
    private String memberName;
    private String email;
    private String major; // 주 분야
    private MemberState memberState;
}
