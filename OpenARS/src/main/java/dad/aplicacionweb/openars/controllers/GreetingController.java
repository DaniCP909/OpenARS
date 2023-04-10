package dad.aplicacionweb.openars.controllers;

import org.springframework.stereotype.Controller;


import dad.aplicacionweb.openars.models.Resource;
        import dad.aplicacionweb.openars.services.ResourceService;
        import org.hibernate.engine.jdbc.BlobProxy;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.ModelAttribute;
        import org.springframework.web.multipart.MultipartFile;

        import javax.servlet.http.HttpServletRequest;
        import java.io.IOException;
        import java.security.Principal;
        import java.sql.SQLException;


@Controller
public class GreetingController {

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

    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("name", "World");
        return "greeting_template";
    }

}