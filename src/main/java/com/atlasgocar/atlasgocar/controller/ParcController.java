package com.atlasgocar.atlasgocar.controller;

import com.atlasgocar.atlasgocar.entity.Agence;
import com.atlasgocar.atlasgocar.entity.Voiture;
import com.atlasgocar.atlasgocar.service.AgenceService;
import com.atlasgocar.atlasgocar.service.ParcService;
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
public class ParcController {


    @Autowired
    ParcService parcService;

    @GetMapping("/addvoiture")
    public String addvoiture(){
        return "addvoiture";
    }



    @PostMapping("/addvoiture")
    public String addvoiturePost(@ModelAttribute Voiture voiture, RedirectAttributes redirectAttributes,String id){
        if(id!=""){
            voiture.setId(new Long(id));
        }
        boolean voitureIsAdded = parcService.addVoiture(voiture);
        if(voitureIsAdded){
            redirectAttributes.addFlashAttribute("voitureAdded",true);
        }
        else{
            redirectAttributes.addFlashAttribute("voitureNotAdded",true);
        }

        return "redirect:/addvoiture";
    }


    @GetMapping("/getallvoitures")
    public String getAllVoitures(Model model){

        List<Voiture> allVoitures = parcService.getAllVoitures();
        model.addAttribute("voitures",allVoitures);
        return "getallvoitures";
    }


    @GetMapping("/deletevoiture")
    public String deleteVoitureById(@RequestParam(name = "id")int id, RedirectAttributes redirectAttributes){
        parcService.deletedVoitureById(new Long(id));
        redirectAttributes.addFlashAttribute("voitureDeleted",true);
        return "redirect:/getallvoitures";
    }

}
