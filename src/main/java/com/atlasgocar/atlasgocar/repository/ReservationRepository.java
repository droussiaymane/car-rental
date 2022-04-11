package com.atlasgocar.atlasgocar.repository;

import com.atlasgocar.atlasgocar.entity.Agence;
import com.atlasgocar.atlasgocar.entity.Reservation;
import com.atlasgocar.atlasgocar.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> findAllByDateDebutLessThanEqualAndDateFinGreaterThanEqual(Date dateDebut, Date dateFin);

    List<Reservation> findAllByAgence(Agence agence);
}
