package sidepj.learningchain.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class ContentsBookmark {

  @Id @GeneratedValue
  @Column(name = "contents_bookmark_id")
  private Long id;
  private Long contentsId;
  private Long userId;
}
