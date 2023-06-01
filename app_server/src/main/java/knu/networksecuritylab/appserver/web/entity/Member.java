package knu.networksecuritylab.appserver.web.entity;

import knu.networksecuritylab.appserver.app.entity.book.BookTag;
import knu.networksecuritylab.appserver.web.entity.dto.MemberRequestDto;
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

    @OneToMany(mappedBy = "member")
    private List<ThesisMember> thesisMembers = new ArrayList<>();

    @Builder
    public Member(String memberName, String email, String major, MemberState memberState) {
        this.memberName = memberName;
        this.email = email;
        this.major = major;
        this.memberState = memberState;
    }

    public static Member from(MemberRequestDto memberRequestDto) {
        return Member.builder()
                .memberName(memberRequestDto.getMemberName())
                .email(memberRequestDto.getEmail())
                .major(memberRequestDto.getMajor())
                .memberState(memberRequestDto.getMemberState())
                .build();
    }
}
