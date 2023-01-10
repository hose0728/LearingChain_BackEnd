package sidepj.learningchain.controller;

import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sidepj.learningchain.domain.dto.ContentsRequest;
import sidepj.learningchain.domain.dto.ContentsResponse;
import sidepj.learningchain.service.ContentsService;

@RestController
@RequiredArgsConstructor
public class ContentsController {

  private final ContentsService contentsService;

  @PostMapping("/api/contents")
  public ContentsResponse saveContents(@RequestBody @Valid ContentsRequest request){
    return contentsService.saveContents(request);
  }

  @GetMapping("/api/contents")
  public List<ContentsResponse> getContentsList() {
    return contentsService.findAllContents();
  }

  @GetMapping("/api/contents/{id}")
  public ContentsResponse getContents(@PathVariable("id") Long id) {
    return contentsService.findContents(id);
  }
}
