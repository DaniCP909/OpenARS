package dad.aplicacionweb.openars.services;

import dad.aplicacionweb.openars.models.Comment;
import dad.aplicacionweb.openars.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepo;

    public List<Comment> findAll(){
        return commentRepo.findAll();
    }
    public Optional<Comment> findById(Long id){
        return commentRepo.findById(id);
    }

    public void save(Comment comment){
        commentRepo.save(comment);
    }
    public void deleteById(Long id){
        commentRepo.deleteById(id);
    }

}
