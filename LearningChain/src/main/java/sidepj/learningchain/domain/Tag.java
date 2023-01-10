package sidepj.learningchain.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Tag {

  @Id @GeneratedValue
  private Long id;
  private String name;

  @OneToMany(mappedBy = "tag")
  private Set<ContentsTag> contents = new HashSet<>();

  @Builder
  public Tag(String name) {
    this.name = name;
  }

}
