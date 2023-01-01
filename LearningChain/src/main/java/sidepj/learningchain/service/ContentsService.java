package sidepj.learningchain.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sidepj.learningchain.domain.Contents;
import sidepj.learningchain.domain.ContentsTag;
import sidepj.learningchain.domain.dto.ContentsRequest;
import sidepj.learningchain.domain.dto.ContentsResponse;
import sidepj.learningchain.repository.ContentsRepository;
import sidepj.learningchain.repository.ContentsTagRepository;
import sidepj.learningchain.repository.TagRepository;

@Service
@RequiredArgsConstructor
public class ContentsService {

  private final ContentsRepository contentsRepository;
  private final ContentsTagRepository contentsTagRepository;
  private final TagRepository tagRepository;

  @Transactional
  public ContentsResponse saveContents(ContentsRequest request) {
    Contents contents = request.toEntity();
    Contents save = contentsRepository.save(contents);

    List<ContentsTag> contentsTags = contents.getTags();

    for(ContentsTag contentsTag : contentsTags) {
      tagRepository.save(contentsTag.getTag());
      contentsTagRepository.save(contentsTag);
    }

    return save.toResponseDto();
  }

  @Transactional
  public List<ContentsResponse> findAllContents() {
    List<Contents> contentsList = contentsRepository.findAll();
    List<ContentsResponse> responseList = new ArrayList<>();

    for(Contents contents  : contentsList) {
      responseList.add(contents.toResponseDto());
    }

    return responseList;
  }


  @Transactional
  public ContentsResponse findContents(Long id) {
    return contentsRepository.findContentsById(id).toResponseDto();
  }




}
