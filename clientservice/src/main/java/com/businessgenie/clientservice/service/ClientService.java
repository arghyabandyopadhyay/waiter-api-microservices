package com.businessgenie.clientservice.service;


import com.businessgenie.clientservice.dto.ClientUserAllocationDTO;
import com.businessgenie.clientservice.model.Clients;
import com.businessgenie.clientservice.util.exception.NoClientExistsException;
import com.businessgenie.clientservice.util.exception.ClientAlreadyExistsException;
import com.businessgenie.clientservice.util.exception.ClientNotExistsException;
import java.util.List;
import java.util.UUID;

public interface ClientService {

    public Clients createClient(Clients client) throws ClientAlreadyExistsException;
    public List<Clients> getAllClients() throws NoClientExistsException;
    public List<ClientUserAllocationDTO> getAllWaiter(String clientId) throws NoClientExistsException;
    public Clients getClient(UUID uuid) throws ClientNotExistsException;
    public Clients updateClient(Clients client)  throws ClientNotExistsException;
    public void deleteClient(UUID uuid) throws ClientNotExistsException;
}
