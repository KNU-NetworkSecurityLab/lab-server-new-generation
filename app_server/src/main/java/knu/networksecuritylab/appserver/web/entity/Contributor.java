package knu.networksecuritylab.appserver.web.entity;

import knu.networksecuritylab.appserver.app.entity.book.BookTag;
import knu.networksecuritylab.appserver.app.entity.book.Tag;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@NoArgsConstructor
@Getter
public class Contributor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contributor_id")
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "contributor")
    private final List<ThesisContributor> thesisContributors = new ArrayList<>();

    @Builder
    private Contributor(String name) {
        this.name = name;
    }

    public static Contributor from(String name) {
        return Contributor.builder()
                .name(name)
                .build();
    }
}
