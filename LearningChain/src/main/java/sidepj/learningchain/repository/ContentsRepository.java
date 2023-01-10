package sidepj.learningchain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sidepj.learningchain.domain.Contents;

public interface ContentsRepository extends JpaRepository<Contents, Long> {
  Contents findContentsById(Long id);
}
