package com.atlasgocar.atlasgocar.service;

import com.atlasgocar.atlasgocar.entity.Agence;
import com.atlasgocar.atlasgocar.entity.User;
import com.atlasgocar.atlasgocar.repository.AgenceRepository;
import com.atlasgocar.atlasgocar.sharedDto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgenceServiceImpl implements AgenceService{

    @Autowired
    AgenceRepository agenceRepository;

    @Override
    public boolean addAgence(Agence agence) {


        Agence agence1 = agenceRepository.save(agence);
        if(agence1!=null){
            return true;
        }
        return false;
    }

    @Override
    public List<Agence> getAllAgent() {
        List<Agence> agences = agenceRepository.findAll();
        return agences;
    }

    @Override
    public void deletedAgenceById(Long id) {
        Boolean existsById = agenceRepository.existsById(id);

        if(!existsById){
            throw new Error("L'agence est introuvable");
        }
        else{
            agenceRepository.deleteById(id);
        }
    }

    @Override
    public Agence findAgenceById(Long id) {
        return agenceRepository.findById(id).get();
    }
}
