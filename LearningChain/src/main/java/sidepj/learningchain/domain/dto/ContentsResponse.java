package sidepj.learningchain.domain.dto;

import java.util.Set;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class ContentsResponse {
  private Long id;
  private char type;
  private char category;
  private String title;
  private String body;
  private String url;
  private Set<String> tags;

  @Builder
  public ContentsResponse(Long id, char type, char category, String title, String body, String url,
      Set<String> tags) {
    this.id = id;
    this.type = type;
    this.category = category;
    this.title = title;
    this.body = body;
    this.url = url;
    this.tags = tags;
  }
}
