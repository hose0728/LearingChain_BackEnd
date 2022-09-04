package sidepj.learningchain.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import sidepj.learningchain.domain.converter.JsonArrayToListConverter;

@Entity
@Getter @Setter
public class Contents {

  @Id @GeneratedValue
  @Column(name = "contentsId")
  private Long id;
  @Column(name = "contentsType")
  private char type;
  @Column(name = "contentsCategory")
  private char category;
  @Column(name = "contentsTitle")
  private String title;
  @Column(name = "contentsBody")
  private String body;
  @Column(name = "contentsUrl")
  private String url;
  @Column(name = "contentsTags")
  @Convert(converter = JsonArrayToListConverter.class)
  private List<String> tags;
  @Column(name = "contentsRecCnt")
  private int recCnt;
  @Column(name = "contentsNrcCnt")
  private int nrcCnt;
}
