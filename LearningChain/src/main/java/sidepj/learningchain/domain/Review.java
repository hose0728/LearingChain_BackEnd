package sidepj.learningchain.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Review {

  @Id @GeneratedValue
  private Long id;
  @ManyToOne
  @JoinColumn(name = "contents_id")
  private Contents contents;
  private char recommend;
  private String oneLineReview;
  private String goodReview;
  private String badReview;
  private Integer likeCount;
}
