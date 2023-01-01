package sidepj.learningchain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sidepj.learningchain.domain.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
