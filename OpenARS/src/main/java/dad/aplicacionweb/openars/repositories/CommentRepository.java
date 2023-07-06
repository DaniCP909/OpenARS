package dad.aplicacionweb.openars.repositories;

import dad.aplicacionweb.openars.models.Comment;
import dad.aplicacionweb.openars.models.Resource;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@CacheConfig(cacheNames="comments")
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Cacheable
    List<Comment> findAll();


    Optional<Comment> findById(Long id);

    @CacheEvict(allEntries = true)
    void deleteById(Long id);

    @CacheEvict(allEntries = true)
    Comment save(Comment comment);

}
