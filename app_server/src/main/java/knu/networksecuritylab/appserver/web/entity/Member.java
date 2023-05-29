package knu.networksecuritylab.appserver.web.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    @NotBlank(message = "이름은 비어있을 수 없습니다.")
    private String memberName;
    private String email;
    private String major; // 주 분야
    private MemberState memberState;
}
