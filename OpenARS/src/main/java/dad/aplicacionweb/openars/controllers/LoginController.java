package dad.aplicacionweb.openars.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/loginerror")
    public String loginerror(){
        return "loginerror";
    }


}
