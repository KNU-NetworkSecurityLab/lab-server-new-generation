package knu.networksecuritylab.appserver.web.entity.dto;

import knu.networksecuritylab.appserver.web.entity.MemberState;
import lombok.*;

@Getter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {
    private String memberName;
    private String email;
    private String major; // 주 분야
    private MemberState memberState;
}
