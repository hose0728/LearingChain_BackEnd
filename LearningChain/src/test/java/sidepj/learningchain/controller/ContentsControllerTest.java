package sidepj.learningchain.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import sidepj.learningchain.common.DocumentUtil;
import sidepj.learningchain.domain.dto.ContentsRequest;
import sidepj.learningchain.service.ContentsService;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class ContentsControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  ContentsService contentsService;

    @Test
    public void saveContents() throws Exception {
      //given
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



      //when
      mockMvc.perform(
          post("/api/contents")
              .contentType(MediaType.APPLICATION_JSON)
              .content(objectMapper.writeValueAsString(dto))
      )
      //then
          .andExpect(status().isOk())
          .andDo(
              document(
                  "contents-post",
                  preprocessRequest(prettyPrint()),
                  preprocessResponse(prettyPrint()),
                  requestFields(
                      DocumentUtil.createRequestField("type", JsonFieldType.STRING,"콘텐츠 유형\r\nB: book, A: article, V: video", ContentsRequest.class),
                      DocumentUtil.createRequestField("category", JsonFieldType.STRING, "콘텐츠 분류\r\n1: 개발, 2: 기획, 3: 디자인, 4: 스타트업", ContentsRequest.class),
                      DocumentUtil.createRequestField("title", JsonFieldType.STRING, "콘텐츠 제목", ContentsRequest.class),
                      DocumentUtil.createRequestField("body", JsonFieldType.STRING, "콘텐츠 본문", ContentsRequest.class),
                      DocumentUtil.createRequestField("url", JsonFieldType.STRING, "콘텐츠 URL", ContentsRequest.class),
                      DocumentUtil.createRequestField("tags", JsonFieldType.ARRAY, "콘텐츠 태그", ContentsRequest.class)
                  ),
                  responseFields(
                      DocumentUtil.createResponseField("id", "콘텐츠 ID"),
                      DocumentUtil.createResponseField("type", "콘텐츠 유형"),
                      DocumentUtil.createResponseField("category", "콘텐츠 분류"),
                      DocumentUtil.createResponseField("title", "콘텐츠 제목"),
                      DocumentUtil.createResponseField("body", "콘텐츠 본문"),
                      DocumentUtil.createResponseField("url", "콘텐츠 URL"),
                      DocumentUtil.createResponseField("tags", "콘텐츠 태그")
                  )
              )
          )
      ;
    }

    @Test
    public void getContentsList() throws Exception {
      //given
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

      contentsService.saveContents(dto);

      tags.clear();
      tags.add("개발자");
      tags.add("개발초보");
      tags.add("입문");

      dto = ContentsRequest.builder()
          .type('B')
          .category('1')
          .title("개발자를 위한 도서 추천")
          .body("개발 입문할 때 참고했던 도서 추천해 드립니다! \u200D\uD83D\uDCDA 베이직한 내용들이 보기좋게 잘 정리되어 있어 가볍게 읽기 좋아요.")
          .url("https://product.kyobobook.co.kr/detail/S000001033125")
          .tags(tags)
          .build();

      contentsService.saveContents(dto);

      //when
      mockMvc.perform(
              get("/api/contents")
                  .contentType(MediaType.APPLICATION_JSON)
                  //.content("{\"none\": \"requestFiled 개발중입니다.\"}")
                  .content("{}")
          )
          //then
          .andExpect(status().isOk())
          .andDo(
              document(
                  "contents-get",
                  preprocessRequest(prettyPrint()),
                  preprocessResponse(prettyPrint()),
                  requestFields(
                      //fieldWithPath("none").type("String").description("검색어, 정렬조건, 태그 등이 포함될 예정입니다.").attributes(
                          //Attributes.key("constraint").value(""))
                  ),
                  responseFields(
                      DocumentUtil.createResponseField("[].id", "콘텐츠 ID"),
                      DocumentUtil.createResponseField("[].type", "콘텐츠 유형"),
                      DocumentUtil.createResponseField("[].category", "콘텐츠 분류"),
                      DocumentUtil.createResponseField("[].title", "콘텐츠 제목"),
                      DocumentUtil.createResponseField("[].body", "콘텐츠 본문"),
                      DocumentUtil.createResponseField("[].url", "콘텐츠 URL"),
                      DocumentUtil.createResponseField("[].tags", "콘텐츠 태그"))
              )
          )
      ;
    }
  @Test
  public void getContents() throws Exception {
    //given
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

    contentsService.saveContents(dto);

    //when
    mockMvc.perform(
            get("/api/contents/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
        )
        //then
        .andExpect(status().isOk())
        .andDo(
            document(
                "contents-get-one",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                pathParameters(
                    parameterWithName("id").description("콘텐츠 ID")
                ),
                responseFields(
                    DocumentUtil.createResponseField("id", "콘텐츠 ID"),
                    DocumentUtil.createResponseField("type", "콘텐츠 유형"),
                    DocumentUtil.createResponseField("category", "콘텐츠 분류"),
                    DocumentUtil.createResponseField("title", "콘텐츠 제목"),
                    DocumentUtil.createResponseField("body", "콘텐츠 본문"),
                    DocumentUtil.createResponseField("url", "콘텐츠 URL"),
                    DocumentUtil.createResponseField("tags", "콘텐츠 태그")
                )
            )
        )
    ;
  }
}
