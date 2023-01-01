package sidepj.learningchain.Service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import sidepj.learningchain.domain.dto.ContentsRequest;
import sidepj.learningchain.domain.dto.ContentsResponse;
import sidepj.learningchain.service.ContentsService;

@SpringBootTest
@Transactional
@Rollback(false)
public class ContentsServiceTest {
  @Autowired
  ContentsService contentsService;

  @Autowired
  EntityManager entityManager;

  @Test
  public void saveContents() {
    List<String> tags = new ArrayList<>();

    tags.add("도서리뷰");
    tags.add("입문자");

    ContentsRequest dto = ContentsRequest.builder()
        .type('B')
        .category('3')
        .title("UIUX 입문자를 위한 도서 추천")
        .body("UIUX 입문할 때 참고했던 도서 추천해 드립니다! \u200D\uD83D\uDCDA 베이직한 내용들이 보기좋게 잘 정리되어 있어 가볍게 읽기 좋아요.")
        .url("http://www.yes24.com/Product/Goods/92426632")
        .tags(tags)
        .build();

    ContentsResponse response = contentsService.saveContents(dto);
    entityManager.flush();
    entityManager.clear();

    System.out.println("response: " + response.toString());
    entityManager.flush();
    entityManager.clear();
    System.out.println("getList: " + contentsService.findAllContents().toString());
    entityManager.flush();
    entityManager.clear();
    System.out.println("getOne: " + contentsService.findContents(response.getId()));
  }


}
