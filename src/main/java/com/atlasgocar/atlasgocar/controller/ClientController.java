package com.atlasgocar.atlasgocar.controller;

import com.atlasgocar.atlasgocar.entity.Client;
import com.atlasgocar.atlasgocar.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("/addclient")
    public String addclient(){
        return "addclient";
    }



    @PostMapping("/addclient")
    public String addclientPost(@ModelAttribute Client client, RedirectAttributes redirectAttributes){
        boolean clientIsAdded = clientService.addClient(client);
        if(clientIsAdded){
            redirectAttributes.addFlashAttribute("clientAdded",true);
        }
        else{
            redirectAttributes.addFlashAttribute("clientNotAdded",true);
        }

        return "redirect:/addclient";
    }


    @GetMapping("/getallclients")
    public String getAllClients(Model model){

        List<Client> allClients = clientService.getAllClients();

        model.addAttribute("clients",allClients);
        return "getallclients";
    }


}
