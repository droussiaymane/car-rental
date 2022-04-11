package com.atlasgocar.atlasgocar.service;

import com.atlasgocar.atlasgocar.entity.Agence;
import com.atlasgocar.atlasgocar.entity.Reservation;
import com.atlasgocar.atlasgocar.entity.Voiture;
import com.atlasgocar.atlasgocar.repository.ReservationRepository;
import com.atlasgocar.atlasgocar.repository.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ReservationServiceImpl implements ReservationService{
    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    VoitureRepository voitureRepository;

    @Override
    public boolean addReservation(Reservation reservation) {
        Date dateDebut = reservation.getDateDebut();
        Date dateFin=reservation.getDateFin();
        if(dateDebut.compareTo(dateFin)>0){
            return false;
        }
        List<Voiture> voituresNotReserved = getVoituresFilterByDate(dateDebut,dateFin);
        if(voituresNotReserved.contains(reservation.getVoiture())){
            reservationRepository.save(reservation);
            return true;
        }

        return false;
    }

    @Override
    public List<Voiture> getVoituresFilterByDate(Date dateDebut,Date dateFin) {
        Date myDateCheck=dateDebut;
        List<Reservation> myReservationList= Arrays.asList();
        while(!myDateCheck.equals(dateFin)){
            List<Reservation> reservationList = reservationRepository.findAllByDateDebutLessThanEqualAndDateFinGreaterThanEqual(myDateCheck, myDateCheck);
            myReservationList = Stream.concat(myReservationList.stream(), reservationList.stream())
                    .collect(Collectors.toList());
            myDateCheck = java.sql.Date.valueOf(Instant.ofEpochMilli(myDateCheck.getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate().plusDays(1));
        }
        List<Reservation> reservationList = reservationRepository.findAllByDateDebutLessThanEqualAndDateFinGreaterThanEqual(dateFin, dateFin);
        myReservationList.addAll(reservationList);
        List<Voiture> voituresReservee = myReservationList.stream().filter(reservation -> !reservation.getStatus().equals("Réservée")).map(reservation -> reservation.getVoiture()).collect(Collectors.toList());
        List<Voiture> voituresNotReserve = voitureRepository.findAll().stream().filter(voiture -> !voituresReservee.contains(voiture)).collect(Collectors.toList());
        return voituresNotReserve;
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation findReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id).get();

        return reservation;
    }

    @Override
    public void updateReservationById(Long id,String status) {
        Reservation reservation = reservationRepository.findById(id).get();
        reservation.setStatus(status);
        reservationRepository.save(reservation);
    }

    @Override
    public void deletedReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id).get();
        reservationRepository.delete(reservation);

    }

    @Override
    public List<Reservation> findReservationsByAgence(Agence agence) {
        List<Reservation> reservations = reservationRepository.findAllByAgence(agence);
        return reservations;
    }
}
