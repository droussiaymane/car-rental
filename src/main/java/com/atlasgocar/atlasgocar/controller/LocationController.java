package com.atlasgocar.atlasgocar.controller;

import com.atlasgocar.atlasgocar.entity.*;
import com.atlasgocar.atlasgocar.repository.ReservationRepository;
import com.atlasgocar.atlasgocar.repository.VoitureRepository;
import com.atlasgocar.atlasgocar.service.*;
import com.atlasgocar.atlasgocar.sharedDto.Daterequest;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
public class LocationController {
    @Autowired
    ParcService parcService;

    @Autowired
    UserService userService;

    @Autowired
    ClientService clientService;

    @Autowired
    ReservationService reservationService;


    @Autowired
    PaiementService paiementService;
    
    @GetMapping("/addreservation")
    public String addvoiture(Model model,@RequestParam(name = "immatriculation",required = false)String immatriculation){
        List<Voiture> voitures = parcService.getAllVoitures();
        List<Client> clients = clientService.getAllClients();
        model.addAttribute("clients",clients);
        model.addAttribute("voitures",voitures);


        return "addreservation";
    }

    @PostMapping("/addreservation")
    public String addvoiturePost(@ModelAttribute Reservation reservation, RedirectAttributes redirectAttributes,String voitureImmatriculation,String clientId,String paiementStatus,Long montantDejaPaye
    ,String nom,String prixParJour,String etatVoiture,String mode,String permis,String prenom,String telephone,Date dateDeNaissance,String cin,Date dateFinCin,String passeport,Date dateDebutPasseport,Date dateDebutPermis,String hotel,String adresse,String chambre){
        // a faire dans une classe Service !! important
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(authentication.getName());
        if(voitureImmatriculation==null){
            redirectAttributes.addFlashAttribute("voitureAchoisir",true);
            return "redirect:/addreservation";
        }
        Voiture voiture = parcService.findByImmatriculation(voitureImmatriculation);
        reservation.setVoiture(voiture);
        Client cLient;
        System.out.println(clientId);
        if(clientId==null){
            if(cin==null && passeport==null && permis==null){
                redirectAttributes.addFlashAttribute("clientAchoisir",true);
                return "redirect:/addreservation";
            }
            else{


            cLient=new Client();
            cLient.setPermis(permis);
            cLient.setAdresse(adresse);
            cLient.setDateDebutPasseport(dateDebutPasseport);
            cLient.setChambre(chambre);
            cLient.setCin(cin);
            cLient.setDateDebutPermis(dateDebutPermis);
            cLient.setDateDeNaissance(dateDeNaissance);

            cLient.setNom(nom);
            cLient.setPrenom(prenom);
            cLient.setTelephone(telephone);
            cLient.setPasseport(passeport);
            cLient.setDateFinCin(dateFinCin);
            cLient.setHotel(hotel);
            cLient = clientService.addClientObject(cLient);
            }
        }
        else if(clientId.equals("notselected")){
            redirectAttributes.addFlashAttribute("clientAchoisir",true);
            return "redirect:/addreservation";
        }


        else{
             cLient = clientService.findById(new Long(clientId));


        }
        reservation.setEtatVoiture(etatVoiture);
        cLient.setReservations(Arrays.asList(reservation));
        reservation.setClient(cLient);
        Paiement paiement=new Paiement();
        paiement.setStatus(paiementStatus);
        paiement.setMontantDejaPaye(montantDejaPaye);
        paiement.setMode(mode);
        Long days= Math.abs(reservation.getDateDebut().getTime()-reservation.getDateFin().getTime());
        paiement.setMontantTotal(new Long(prixParJour)* TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS));
        paiement.setReste(paiement.getMontantTotal()-paiement.getMontantDejaPaye());
        if(paiement.getReste().equals(new Long(0))){
            paiement.setStatus("Réglé");
            paiement.setDateDePaiement(java.sql.Date.valueOf(LocalDate.now()));
        }
        System.out.println(paiement.getReste().equals(new Long(0)));

        reservation.setAgence(user.getAgence());
        paiement.setReservation(reservation);
        boolean reservationIsAdded = reservationService.addReservation(reservation);
        if(reservationIsAdded){
            redirectAttributes.addFlashAttribute("reservationAdded",true);
            paiementService.addPaiement(paiement);


        }
        else{
            redirectAttributes.addFlashAttribute("reservationNotAdded",true);

        }

        return "redirect:/addreservation";
    }



    @GetMapping("/deletereservation")
    public String deleteClientById(@RequestParam(name = "id")int id, RedirectAttributes redirectAttributes){
        reservationService.deletedReservationById(new Long(id));
        redirectAttributes.addFlashAttribute("reservationDeleted",true);
        return "redirect:/getallreservations";
    }


    @GetMapping("/voirdisponnibilite")
    public String getdisponnibilitePage(){

        return "voirdisponnibilite";
    }
    @GetMapping("/disponnibilitepardate")
    public String getdisponnibilitepardate(){

        return "disponnibilitepardate";
    }
    @PostMapping("/pardate")
    public String parDate(@ModelAttribute Daterequest daterequest,Model model,RedirectAttributes redirectAttributes){
        Date dateDebut=daterequest.getDateDebut();
        Date dateFin=daterequest.getDateFin();

        if(dateDebut==null || dateFin==null || dateDebut.compareTo(dateFin)>0){
            redirectAttributes.addFlashAttribute("chooseDate",true);
            return "redirect:/disponnibilitepardate";
        }
        List<Voiture> voituresNotReserved = reservationService.getVoituresFilterByDate(dateDebut,dateFin);
        model.addAttribute("voituresNotReserved",voituresNotReserved);
        return "disponnibilitepardateData";
    }
    @GetMapping("/pardate")
    public String redirectDisponnibilitepardate(){

        return "redirect:/disponnibilitepardate";
    }


    @GetMapping("/getallreservations")
    public String getallreservations(Model model){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email);
        List<Reservation> allReservations=new ArrayList<Reservation>();
        if(user.getRole().equals("ROLE_ADMIN")){
           allReservations = reservationService.getAllReservations();

        }
        else{
            allReservations = reservationService.findReservationsByAgence(user.getAgence());
        }

        model.addAttribute("allReservations",allReservations);
        return "getallreservations";
    }

    @PostMapping("/statusmodified")
    public String updateStatus(String reservationId,String status,RedirectAttributes redirectAttributes){

        reservationService.updateReservationById(new Long(reservationId),status);
        redirectAttributes.addFlashAttribute("reservationStatusUpdated",true);

        return "redirect:/getallreservations";
    }
    @GetMapping("/statusmodified")
    public String getUpdateStatus(){

        return "redirect:/getallreservations";
    }

    @GetMapping("/downloadcontrat")
    public String downloadRecu(@RequestParam(name = "id")int id,Model model){
        Reservation reservation = reservationService.findReservationById(new Long(id));
        model.addAttribute("reservation",reservation);
        return "downloadcontrat";
    }

}
