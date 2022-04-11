package com.atlasgocar.atlasgocar.controller;

import com.atlasgocar.atlasgocar.entity.Agence;
import com.atlasgocar.atlasgocar.service.AgenceService;
import com.atlasgocar.atlasgocar.service.UserService;
import com.atlasgocar.atlasgocar.sharedDto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UtilisateurController {

    @Autowired
    UserService userService;

    @Autowired
    AgenceService agenceService;

    @GetMapping("/addagent")
    public String addagent(Model model){
        List<Agence> agences = agenceService.getAllAgent();
        model.addAttribute("agences",agences);
        return "addagent";
    }

    @PostMapping("/addagent")
    public String addagentPost(@ModelAttribute UserDto userDto, RedirectAttributes redirectAttributes,String agenceId){
        Agence agence = agenceService.findAgenceById(new Long(agenceId));

        boolean userIsAdded = userService.addUser(userDto,agence);
        if(userIsAdded){
            redirectAttributes.addFlashAttribute("userAdded",true);
        }
        else{
            redirectAttributes.addFlashAttribute("userNotAdded",true);
        }

        return "redirect:/addagent";
    }

    @GetMapping("/getallagent")
    public String getAllAgents(Model model){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<UserDto> allAgent = userService.getAllAgent();
        List<UserDto> agents = allAgent.stream().filter(agent -> !agent.getEmail().equals(authentication.getName())).collect(Collectors.toList());
        model.addAttribute("users",agents);
        return "getallagent";
    }

    @GetMapping("/deleteagent")
    public String deleteAgentById(@RequestParam(name = "id")int id, RedirectAttributes redirectAttributes){
        userService.deletedAgentById(new Long(id));
        redirectAttributes.addFlashAttribute("userDeleted",true);
        return "redirect:/getallagent";
    }
}
