package com.atlasgocar.atlasgocar.controller;

import com.atlasgocar.atlasgocar.entity.Paiement;
import com.atlasgocar.atlasgocar.entity.Reservation;
import com.atlasgocar.atlasgocar.entity.Voiture;
import com.atlasgocar.atlasgocar.service.PaiementService;
import com.atlasgocar.atlasgocar.service.ParcService;
import com.atlasgocar.atlasgocar.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DashboardController {

    @Autowired
    ParcService parcService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    PaiementService paiementService;




    @GetMapping("/dashboard")
    public String getDashboard(Model model){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user_name",authentication.getName());
        List<Voiture> voituresFilterByDate = reservationService.getVoituresFilterByDate(Date.valueOf(LocalDate.now()),Date.valueOf(LocalDate.now().plusDays(1)));
        int voituresDisponniblesNumber = voituresFilterByDate.size();
        List<Reservation> allReservations = reservationService.getAllReservations();
        int reservationsterminéeNumber = allReservations.stream().filter(reservation -> reservation.getStatus().equals("Terminée")).collect(Collectors.toList()).size();
        int reservationsConfirméesNumber = allReservations.stream().filter(reservation -> reservation.getStatus().equals("Confirmée")).collect(Collectors.toList()).size();
        List<Paiement> allPaiementRegle = paiementService.getAllPaiement().stream().filter(paiement -> paiement.getStatus().equals("Réglé")).collect(Collectors.toList());
        int chiffreAffaire = allPaiementRegle.stream().mapToInt(paiement -> Math.toIntExact((Long) paiement.getMontantTotal())).sum();
        model.addAttribute("voituresdisponnibles",voituresDisponniblesNumber);
        model.addAttribute("Reservationstermined",reservationsterminéeNumber);
        model.addAttribute("ReservationsConfirmed",reservationsConfirméesNumber);
        model.addAttribute("paiementRegle",chiffreAffaire);
        return "index";
    }


}
