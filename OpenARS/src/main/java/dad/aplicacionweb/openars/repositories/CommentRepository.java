package dad.aplicacionweb.openars.repositories;

import dad.aplicacionweb.openars.models.Comment;
import dad.aplicacionweb.openars.models.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAll();
    Optional<Comment> findById(Long id);


    void deleteById(Long id);

}
