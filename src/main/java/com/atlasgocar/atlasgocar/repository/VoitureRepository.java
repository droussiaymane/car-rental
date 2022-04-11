package com.atlasgocar.atlasgocar.repository;

import com.atlasgocar.atlasgocar.entity.Agence;
import com.atlasgocar.atlasgocar.entity.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoitureRepository extends JpaRepository<Voiture,Long> {
    Optional<Voiture> findByImmatriculation(String immatriculation);
    boolean existsByImmatriculation(String immatriculation);
}
