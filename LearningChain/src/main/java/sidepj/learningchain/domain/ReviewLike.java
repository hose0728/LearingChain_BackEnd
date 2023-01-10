package sidepj.learningchain.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter@ Setter
public class ReviewLike {

  @Id @GeneratedValue
  @Column(name = "review_like_id")
  private Long id;
  private Long reviewId;
  private Long userId;
}
