package com.atlasgocar.atlasgocar.repository;

import com.atlasgocar.atlasgocar.entity.Agence;
import com.atlasgocar.atlasgocar.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgenceRepository extends JpaRepository<Agence,Long> {
    Optional<Agence> findByNom(String nom);
    boolean existsByNom(String nom);
}
