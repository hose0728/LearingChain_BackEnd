package sidepj.learningchain.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sidepj.learningchain.domain.dto.ContentsResponse;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Contents {

  @Id @GeneratedValue
  private Long id;
  private char type;
  private char category;
  private String title;
  private String body;
  private String url;
  private int goodCount;
  private int badCount;
  private int bookmarkCount;
  private int shareCount;

  @OneToMany(mappedBy = "contents")
  private List<ContentsTag> tags;

  @OneToMany(mappedBy = "contents")
  private List<ContentsAttachFile> files;

  @OneToMany(mappedBy = "contents")
  private List<Review> reviews;

  @Builder
  public Contents(Long id, char type, char category, String title, String body, String url,
      int goodCount, int badCount, int bookmarkCount, int shareCount, List<ContentsTag> tags,
      List<ContentsAttachFile> files, List<Review> reviews) {
    this.id = id;
    this.type = type;
    this.category = category;
    this.title = title;
    this.body = body;
    this.url = url;
    this.goodCount = goodCount;
    this.badCount = badCount;
    this.bookmarkCount = bookmarkCount;
    this.shareCount = shareCount;
    this.tags = tags;
    this.files = files;
    this.reviews = reviews;
  }

  public void addContentsTag(List<ContentsTag> contentsTags) {
    this.tags = contentsTags;

    for(ContentsTag contentsTag : contentsTags) {
      contentsTag.setContents(this);
    }
  }

  public ContentsResponse toResponseDto() {
    Set<String> tags = new HashSet<>();

    for(ContentsTag contentsTag : this.getTags()) {
      tags.add(contentsTag.getTag().getName());
    }

    ContentsResponse response = ContentsResponse.builder()
        .id(this.getId())
        .type(this.getType())
        .category(this.getCategory())
        .title(this.getTitle())
        .body(this.getBody())
        .url(this.getUrl())
        .tags(tags)
        .build();

    return response;
  }

}
