package com.atlasgocar.atlasgocar.service;

import com.atlasgocar.atlasgocar.entity.Agence;
import com.atlasgocar.atlasgocar.entity.Voiture;

import java.util.List;

public interface ParcService {
    public boolean addVoiture(Voiture voiture);

    public List<Voiture> getAllVoitures();

    public void deletedVoitureById(Long id);

    public Voiture findByImmatriculation(String immatriculation);
}
