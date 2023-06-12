package knu.networksecuritylab.appserver.web.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ThesisMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "thesis_member_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thesis_id")
    private Thesis thesis;

    @Builder
    public ThesisMember(Member member, Thesis thesis) {
        this.member = member;
        this.thesis = thesis;
    }

    public static ThesisMember from(Member member, Thesis thesis) {
        return ThesisMember.builder()
                .member(member)
                .thesis(thesis)
                .build();
    }
}
