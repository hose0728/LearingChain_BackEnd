package sidepj.learningchain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sidepj.learningchain.domain.ContentsBookmark;

public interface ContentsBookmarkRepository  extends JpaRepository<ContentsBookmark, Long> {

}
