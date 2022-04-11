package com.atlasgocar.atlasgocar.service;

import com.atlasgocar.atlasgocar.entity.Client;
import com.atlasgocar.atlasgocar.entity.Voiture;

import java.util.List;

public interface ClientService {

    public boolean addClient(Client client);
    public Client addClientObject(Client client);

    public List<Client> getAllClients();

    public void deletedClientById(Long id);

    public Client findById(Long id);
}
