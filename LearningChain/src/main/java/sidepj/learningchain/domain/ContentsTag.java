package sidepj.learningchain.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ContentsTag {

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "contents_id")
  private Contents contents;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "tag_id")
  private Tag tag;

  @Builder
  public ContentsTag(Contents contents, Tag tag) {
    this.contents = contents;
    this.tag = tag;
  }

  public void setContents(Contents contents) {
    this.contents = contents;
  }

  public void setTag(Tag tag) {
    this.tag = tag;
  }
}
