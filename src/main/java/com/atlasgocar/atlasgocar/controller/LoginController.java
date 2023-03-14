package com.atlasgocar.atlasgocar.controller;

import com.atlasgocar.atlasgocar.config.AuthenticationSystem;
import com.atlasgocar.atlasgocar.entity.Agence;
import com.atlasgocar.atlasgocar.entity.User;
import com.atlasgocar.atlasgocar.repository.AgenceRepository;
import com.atlasgocar.atlasgocar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.text.html.Option;
import java.util.Optional;

@Controller
public class LoginController {


    @Autowired
    UserRepository userRepository;


    @Autowired
    AgenceRepository agenceRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    @GetMapping({"/login","/login.html"})
    public String login(){
        if (!AuthenticationSystem.isLogged()) return "login";
            return "redirect:/dashboard";
    }

    @GetMapping("/")
    public String home(){
        User user=new User();
        user.setEmail("corisco-car@gmail.com");
        user.setActive(true);
        user.setCin("TAXXXX");
        user.setRole("ROLE_ADMIN");
        user.setPassword(passwordEncoder.encode("corisco-car20232024"));

        Agence agence=new Agence();
        agence.setId(1L);
        agence.setNom("corisco-car agence");
        agence.setVille("casa");
        agence.setCodePostal("13000");

        Optional<Agence> agence1=agenceRepository.findById(agence.getId());

        Optional<User> myuser=userRepository.findByEmail("corisco-car@gmail.com");
        if(!myuser.isPresent()){
            if(agence1.isPresent()){
                user.setAgence(agence1.get());
            }
            else{
                Agence myagence=agenceRepository.save(agence);
                user.setAgence(myagence);
            }

            userRepository.save(user);
        }

        if (!AuthenticationSystem.isLogged()) return "home";
        return "forward:/dashboard";
    }
}
