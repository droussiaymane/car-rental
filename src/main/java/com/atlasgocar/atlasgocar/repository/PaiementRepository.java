package com.atlasgocar.atlasgocar.repository;

import com.atlasgocar.atlasgocar.entity.Agence;
import com.atlasgocar.atlasgocar.entity.Client;
import com.atlasgocar.atlasgocar.entity.Paiement;
import com.atlasgocar.atlasgocar.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long> {



}
