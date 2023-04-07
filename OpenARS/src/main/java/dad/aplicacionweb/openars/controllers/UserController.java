package dad.aplicacionweb.openars.controllers;

import dad.aplicacionweb.openars.models.User;
import dad.aplicacionweb.openars.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    UserService servUsers;

    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("name", "World");
        return "greeting_template";
    }

    @GetMapping("/signup-user")
    public String signupUser(){
        return "/temps_user/signup_user";
    }

    @PostMapping("/signup-user")
    public String signupUserPost(User user){
        servUsers.save(user);
        return "redirect:/";
    }

}
