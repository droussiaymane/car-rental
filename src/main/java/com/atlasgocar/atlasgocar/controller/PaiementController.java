package com.atlasgocar.atlasgocar.controller;

import com.atlasgocar.atlasgocar.entity.Paiement;
import com.atlasgocar.atlasgocar.entity.Reservation;
import com.atlasgocar.atlasgocar.entity.User;
import com.atlasgocar.atlasgocar.entity.Voiture;
import com.atlasgocar.atlasgocar.service.PaiementService;
import com.atlasgocar.atlasgocar.service.ReservationService;
import com.atlasgocar.atlasgocar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PaiementController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    UserService userService;
    @Autowired
    PaiementService paiementService;

    @GetMapping("/getAllreglements")
    public String getAllVoitures(Model model){

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email);
        List<Paiement> paiements=new ArrayList<Paiement>();
        if(user.getRole().equals("ROLE_ADMIN")){
            paiements = paiementService.getAllPaiement();

        }
        else{
            paiements = paiementService.getAllPaiement().stream().filter(paiement -> paiement.getReservation().getAgence().equals(user.getAgence())).collect(Collectors.toList());
        }

        model.addAttribute("paiements",paiements);

        return "getallreglements";
    }

    @GetMapping("/deletepaiement")
    public String deleteVoitureById(@RequestParam(name = "id")int id, RedirectAttributes redirectAttributes){
        paiementService.deletedPaiementById(new Long(id));
        redirectAttributes.addFlashAttribute("paiementDeleted",true);
        return "redirect:/getAllreglements";
    }

    @GetMapping("/paiementregle")
    public String paiementregleById(@RequestParam(name = "id")int id, RedirectAttributes redirectAttributes){
        Paiement paiement = paiementService.getPaiementById(new Long(id));
        Reservation reservation = paiement.getReservation();
        reservationService.updateReservationById(reservation.getId(),"Terminée");
        paiementService.paiementRegled(new Long(id));
        redirectAttributes.addFlashAttribute("paiementRegled",true);
        return "redirect:/getAllreglements";
    }

    @GetMapping("/downloadrecu")
    public String downloadRecu(@RequestParam(name = "id")int id,Model model){
        Paiement paiement = paiementService.getPaiementById(new Long(id));
        model.addAttribute("paiement",paiement);
        return "downloadrecu";
    }


}
