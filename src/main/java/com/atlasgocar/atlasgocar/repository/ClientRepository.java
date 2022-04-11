package com.atlasgocar.atlasgocar.repository;

import com.atlasgocar.atlasgocar.entity.Agence;
import com.atlasgocar.atlasgocar.entity.Client;
import com.atlasgocar.atlasgocar.entity.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    boolean existsByCin(String cin);
    boolean existsByPasseport(String passeport);
    boolean existsByPermis(String permis);

}
