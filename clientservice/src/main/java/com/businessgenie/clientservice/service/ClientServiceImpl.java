package com.businessgenie.clientservice.service;

import com.businessgenie.clientservice.model.Clients;
import com.businessgenie.clientservice.repository.ClientsRepository;
import com.businessgenie.clientservice.util.exception.ClientAlreadyExistsException;
import com.businessgenie.clientservice.util.exception.NoClientExistsException;
import com.businessgenie.clientservice.util.exception.ClientNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientsRepository clientsRepository;
    private final RestTemplate restTemplate;
    @Autowired
    public ClientServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public Clients updateClient(Clients client) throws ClientNotExistsException {
        if(clientsRepository.existsById(client.getId())) return clientsRepository.save(client);
        else throw new ClientNotExistsException();
    }

    @Override
    public void deleteClient(UUID uuid) throws ClientNotExistsException {
        if(clientsRepository.existsById(uuid)) clientsRepository.deleteById(uuid);
        else throw new ClientNotExistsException();
    }

    @Override
    public List<Clients> getAllClients() throws NoClientExistsException {
        return clientsRepository.findAll();
    }

    @Override
    public Clients getClient(UUID uuid) throws ClientNotExistsException {
        return clientsRepository.findById(uuid).orElseThrow(ClientNotExistsException::new);
    }


    @Override
    public Clients createClient(Clients client)
            throws ClientAlreadyExistsException {
        if(clientsRepository.findClient(client.getName(), client.getAddress()) == null)
            return clientsRepository.save(client);
        else throw new ClientAlreadyExistsException();
    }
    
}
