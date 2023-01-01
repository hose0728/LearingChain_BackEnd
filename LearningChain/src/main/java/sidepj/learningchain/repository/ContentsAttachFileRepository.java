package sidepj.learningchain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sidepj.learningchain.domain.Contents;
import sidepj.learningchain.domain.ContentsAttachFile;

public interface ContentsAttachFileRepository  extends JpaRepository<ContentsAttachFile, Long> {

}
