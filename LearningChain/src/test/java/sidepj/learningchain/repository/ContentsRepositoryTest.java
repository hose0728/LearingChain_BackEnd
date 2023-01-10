package sidepj.learningchain.repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import sidepj.learningchain.domain.Contents;
import sidepj.learningchain.domain.ContentsTag;
import sidepj.learningchain.domain.Tag;

@SpringBootTest
@Transactional
@Rollback(false)
public class ContentsRepositoryTest {
  @Autowired
  ContentsRepository contentsRepository;
  @Autowired
  ContentsTagRepository contentsTagRepository;
  @Autowired
  TagRepository tagRepository;

  @Autowired
  EntityManager entityManager;

  @Test
  public void saveContentsTest() {
    Tag tag1 = Tag.builder().name("testTag1").build();
    Tag tag2 = Tag.builder().name("testTag2").build();
    Tag tag3 = Tag.builder().name("testTag3").build();

    tagRepository.save(tag1);
    tagRepository.save(tag2);
    tagRepository.save(tag3);

    Contents contents = Contents.builder()
        .type('B')
        .category('1')
        .title("testTitle")
        .body("테스트 본문입니다.")
        .url("http://127.0.0.1")
        .build();

    ContentsTag contentsTag1 = ContentsTag.builder().contents(contents).tag(tag1).build();
    ContentsTag contentsTag2 = ContentsTag.builder().contents(contents).tag(tag2).build();
    ContentsTag contentsTag3 = ContentsTag.builder().contents(contents).tag(tag3).build();

    List<ContentsTag> tags = new ArrayList<>();

    tags.add(contentsTag1);
    tags.add(contentsTag2);
    tags.add(contentsTag3);

    contents.addContentsTag(tags);

    Contents result = contentsRepository.save(contents);

    contentsTagRepository.save(contentsTag1);
    contentsTagRepository.save(contentsTag2);
    contentsTagRepository.save(contentsTag3);

    entityManager.flush();
    entityManager.clear();

    Contents find = contentsRepository.findContentsById(result.getId());

    List<ContentsTag> findContentsTag = find.getTags();

    for(ContentsTag ct : findContentsTag) {
      System.out.println("result: " + ct.getTag().getName());
    }

    Assertions.assertThat(find.getId()).isEqualTo(contents.getId());
    Assertions.assertThat(find.getType()).isEqualTo(contents.getType());
    Assertions.assertThat(find.getCategory()).isEqualTo(contents.getCategory());
    Assertions.assertThat(find.getTitle()).isEqualTo(contents.getTitle());
    Assertions.assertThat(find.getBody()).isEqualTo(contents.getBody());
    Assertions.assertThat(find.getUrl()).isEqualTo(contents.getUrl());

    for(int i = 0; i < tags.size(); i++) {
      Assertions.assertThat(tags.get(i).getTag().getName()).isEqualTo(findContentsTag.get(i).getTag().getName());
    }
  }
}
