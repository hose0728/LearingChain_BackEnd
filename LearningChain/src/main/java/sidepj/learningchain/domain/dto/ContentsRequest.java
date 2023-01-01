package sidepj.learningchain.domain.dto;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import sidepj.learningchain.domain.Contents;
import sidepj.learningchain.domain.ContentsTag;
import sidepj.learningchain.domain.Tag;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class ContentsRequest {
  @NotNull
  private char type;
  @NotNull
  private char category;
  @NotEmpty
  private String title;
  @NotEmpty
  private String body;
  private String url;
  private List<String> tags;

  @Builder
  public ContentsRequest(char type, char category, String title, String body, String url,
      List<String> tags) {
    this.type = type;
    this.category = category;
    this.title = title;
    this.body = body;
    this.url = url;
    this.tags = tags;
  }

  public Contents toEntity() {
    Contents contents = Contents.builder()
        .type(getType())
        .category(getCategory())
        .title(getTitle())
        .body(getBody())
        .url(getUrl())
        .build();
    List<ContentsTag> contentsTags = new ArrayList<>();

    for(String tagName : getTags()) {
      Tag tag = Tag.builder().name(tagName).build();
      ContentsTag contentsTag = ContentsTag.builder().contents(contents).tag(tag).build();
      contentsTags.add(contentsTag);
    }

    contents.addContentsTag(contentsTags);

    return contents;
  }
}
