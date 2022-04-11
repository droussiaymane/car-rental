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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AgenceController {



    @Autowired
    AgenceService agenceService;

    @GetMapping("/addagence")
    public String addAgence(){


        return "addagence";
    }



    @PostMapping("/addagence")
    public String addAgencePost(@ModelAttribute Agence agence, RedirectAttributes redirectAttributes,String id){
        if(id!=""){
            agence.setId(new Long(id));
        }
        boolean agenceIsAdded = agenceService.addAgence(agence);
        if(agenceIsAdded){
            redirectAttributes.addFlashAttribute("agenceAdded",true);
        }
        else{
            redirectAttributes.addFlashAttribute("agenceNotAdded",true);
        }

        return "redirect:/addagence";
    }


    @GetMapping("/getallagences")
    public String getAllAgences(Model model){

        List<Agence> allAgences = agenceService.getAllAgent();
        model.addAttribute("agences",allAgences);
        return "getallagences";
    }


    @GetMapping("/deleteagence")
    public String deleteAgenceById(@RequestParam(name = "id")int id, RedirectAttributes redirectAttributes){
        agenceService.deletedAgenceById(new Long(id));
        redirectAttributes.addFlashAttribute("agentDeleted",true);
        return "redirect:/getallagences";
    }
}
