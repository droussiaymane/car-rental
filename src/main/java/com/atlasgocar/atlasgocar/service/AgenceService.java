package com.atlasgocar.atlasgocar.service;

import com.atlasgocar.atlasgocar.entity.Agence;
import com.atlasgocar.atlasgocar.sharedDto.UserDto;

import java.util.List;

public interface AgenceService {
    public boolean addAgence(Agence agence);

    public List<Agence> getAllAgent();

    public void deletedAgenceById(Long id);

    public Agence findAgenceById(Long id);

}
