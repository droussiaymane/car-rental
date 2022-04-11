package com.atlasgocar.atlasgocar.controller;

import com.atlasgocar.atlasgocar.config.AuthenticationSystem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @GetMapping({"/login","/login.html"})
    public String login(){
        if (!AuthenticationSystem.isLogged()) return "login";
            return "redirect:/dashboard";
    }

    @GetMapping("/")
    public String home(){
        if (!AuthenticationSystem.isLogged()) return "home";
        return "forward:/dashboard";
    }
}
