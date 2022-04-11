package com.atlasgocar.atlasgocar.service;

import com.atlasgocar.atlasgocar.entity.Agence;
import com.atlasgocar.atlasgocar.entity.Voiture;
import com.atlasgocar.atlasgocar.repository.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParcServiceImpl implements ParcService{
    @Autowired
    VoitureRepository voitureRepository;

    @Override
    public boolean addVoiture(Voiture voiture) {
        Voiture voiture1 = voitureRepository.save(voiture);
        if(voiture1!=null){
            return true;
        }
        return false;
    }

    @Override
    public List<Voiture> getAllVoitures() {
        List<Voiture> voitures = voitureRepository.findAll();
        return voitures;
    }

    @Override
    public void deletedVoitureById(Long id) {
        Boolean existsById = voitureRepository.existsById(id);

        if(!existsById){
            throw new Error("La voiture est introuvable");
        }
        else{
            voitureRepository.deleteById(id);
        }
    }

    @Override
    public Voiture findByImmatriculation(String immatriculation) {
        Voiture voiture = voitureRepository.findByImmatriculation(immatriculation).get();
        return voiture;
    }
}
