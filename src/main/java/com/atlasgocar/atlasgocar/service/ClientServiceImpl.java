package com.atlasgocar.atlasgocar.service;

import com.atlasgocar.atlasgocar.entity.Client;
import com.atlasgocar.atlasgocar.entity.Voiture;
import com.atlasgocar.atlasgocar.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    ClientRepository clientRepository;

    @Override
    public boolean addClient(Client client) {
        boolean existsByNom = clientRepository.existsByCin(client.getCin()) || clientRepository.existsByPermis(client.getPermis()) || clientRepository.existsByPasseport(client.getPasseport());
        if(!existsByNom){

            clientRepository.save(client);
            return true;
        }
        return false;
    }

    @Override
    public List<Client> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients;
    }

    @Override
    public void deletedClientById(Long id) {
        Boolean existsById = clientRepository.existsById(id);

        if(!existsById){
            throw new Error("Le client est introuvable");
        }
        else{
            clientRepository.deleteById(id);
        }
    }

    @Override
    public Client findById(Long id) {
        Client client = clientRepository.findById(id).get();
        return client;
    }

    @Override
    public Client addClientObject(Client client) {
        Client clientSaved = clientRepository.save(client);
        return clientSaved;
    }
}
