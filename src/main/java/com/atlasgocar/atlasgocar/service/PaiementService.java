package com.atlasgocar.atlasgocar.service;

import com.atlasgocar.atlasgocar.entity.Agence;
import com.atlasgocar.atlasgocar.entity.Paiement;

import java.util.List;

public interface PaiementService {
    public boolean addPaiement(Paiement paiement);

    public List<Paiement> getAllPaiement();

    public void deletedPaiementById(Long id);

    public void paiementRegled(Long id);

    public Paiement getPaiementById(Long id);

    public List<Paiement> findPayemntByAgence(Agence agence);
}
