package dad.aplicacionweb.openars.controllers;

import dad.aplicacionweb.openars.CommentInfoDto;
import dad.aplicacionweb.openars.models.Comment;
import dad.aplicacionweb.openars.models.Resource;
import dad.aplicacionweb.openars.models.User;
import dad.aplicacionweb.openars.services.CommentService;
import dad.aplicacionweb.openars.services.ResourceService;
import dad.aplicacionweb.openars.services.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

//@Component
@Controller
public class CommentController {

    @Autowired
    private CommentService commentServ;

    @Autowired
    private UserService userServ;

    @Autowired
    private ResourceService resourceServ;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request){

        Principal principal = request.getUserPrincipal();   //java.security

        if(principal != null){

            model.addAttribute("logged", true);
            model.addAttribute("username", principal.getName());
            model.addAttribute("admin", request.isUserInRole("ADMIN"));     //return boolean

        } else{
            model.addAttribute("logged", false);
        }

    }

    @GetMapping("/all-comments")
    public String showComments(Model model){
        List<Comment> allcomments = commentServ.findAll();
        model.addAttribute("comments", allcomments);

        return "temps_Comment/all-comments";
    }

    @PostMapping("/{id}/addcomment")
    public String addComment(@RequestParam String opinion, HttpServletRequest request, @PathVariable Long id){
        Resource actual = resourceServ.findById(id).get();
        User usactual = userServ.findByUsername(request.getUserPrincipal().getName());
        Comment comment = new Comment(usactual, actual, opinion);
        commentServ.save(comment);

        System.out.println(usactual.getUsername() + " " + actual.getName() + " " + actual.getOwner().getEmail());
        rabbitTemplate.convertAndSend("notifications", new CommentInfoDto(usactual.getUsername(), actual.getName(), actual.getOwner().getEmail()));

        return "redirect:/all-resources/" + id;
    }

    @GetMapping("/{userid}/all-user-comments")
    public String showUserComments(Model model, @PathVariable Long userid){

        Optional<User> user = userServ.findById(userid);
        if(user.isPresent()){
            model.addAttribute("actualuser", user.get());
        }

        return "temps_Comment/all-uscomments";

    }

    @GetMapping("/{id}/edit-comment")
    public String editComment(Model model, @PathVariable Long id){

        Optional<Comment> comment = commentServ.findById(id);

        if(comment.isPresent()){
            model.addAttribute("comment", comment.get());
        }
        return "temps_Comment/edit-comment";
    }

    @PostMapping("/{id}/edit-comment")
    public String editCommentPost(Model model, @PathVariable Long id, Comment comment){
        Optional<Comment> optcomment = commentServ.findById(id);

        comment.setUser(optcomment.orElseThrow().getUser());
        comment.setResource(optcomment.orElseThrow().getResource());

        commentServ.save(comment);


        return "redirect:/profile";
    }

    @GetMapping("/{id}/delete-comment")
    public String deleteComment(Model model, @PathVariable Long id){
        Optional<Comment> comment = commentServ.findById(id);
        if(comment.isPresent()){
            model.addAttribute("rscname", comment.get().getResource().getName());
            commentServ.deleteById(id);
        }
        return "temps_Comment/removed-comment";
    }


}
