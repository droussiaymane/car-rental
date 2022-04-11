package com.atlasgocar.atlasgocar.service;

import com.atlasgocar.atlasgocar.entity.Agence;
import com.atlasgocar.atlasgocar.entity.Client;
import com.atlasgocar.atlasgocar.entity.Paiement;
import com.atlasgocar.atlasgocar.repository.PaiementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PaiementServiceImpl implements PaiementService{

    @Autowired
    PaiementRepository paiementRepository;

    @Override
    public boolean addPaiement(Paiement paiement) {
        paiementRepository.save(paiement);
        return true;

    }

    @Override
    public List<Paiement> getAllPaiement() {
        List<Paiement> paiements = paiementRepository.findAll();
        return paiements;
    }

    @Override
    public void deletedPaiementById(Long id) {
        Boolean existsById = paiementRepository.existsById(id);

        if(!existsById){
            throw new Error("Le paiement est introuvable");
        }
        else{
            paiementRepository.deleteById(id);
        }
    }

    @Override
    public void paiementRegled(Long id) {
        Paiement paiement = paiementRepository.findById(id).get();
        paiement.setStatus("Réglé");
        paiement.setReste(new Long(0));
        paiement.setDateDePaiement(java.sql.Date.valueOf(LocalDate.now()));
        paiementRepository.save(paiement);
    }

    @Override
    public Paiement getPaiementById(Long id) {
        return paiementRepository.findById(id).get();
    }

    @Override
    public List<Paiement> findPayemntByAgence(Agence agence) {
        return null;
    }
}
