package com.atlasgocar.atlasgocar.service;


import com.atlasgocar.atlasgocar.entity.Agence;
import com.atlasgocar.atlasgocar.entity.Reservation;
import com.atlasgocar.atlasgocar.entity.Voiture;

import java.util.Date;
import java.util.List;

public interface ReservationService {
    public boolean addReservation(Reservation reservation);
    public List<Voiture> getVoituresFilterByDate(Date dateDebut,Date dateFin);
    public List<Reservation> getAllReservations();
    public Reservation findReservationById(Long id);
    public void updateReservationById(Long id,String status);
    public void deletedReservationById(Long id);
    public List<Reservation> findReservationsByAgence(Agence agence);


}
