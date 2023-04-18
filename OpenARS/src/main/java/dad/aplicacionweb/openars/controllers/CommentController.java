package dad.aplicacionweb.openars.controllers;

import dad.aplicacionweb.openars.models.Comment;
import dad.aplicacionweb.openars.services.CommentService;
import dad.aplicacionweb.openars.services.ResourceService;
import dad.aplicacionweb.openars.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;


@Controller
public class CommentController {

    @Autowired
    private CommentService commentServ;

    @Autowired
    private UserService userServ;

    @Autowired
    private ResourceService resourceServ;

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
        Comment comment = new Comment(userServ.findByUsername(request.getUserPrincipal().getName()), resourceServ.findById(id).get(), opinion);
        commentServ.save(comment);
        return "redirect:/all-resources/" + id;
    }


}
