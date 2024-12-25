package com.businessgenie.clientservice.controller;

import com.businessgenie.clientservice.model.Clients;
import com.businessgenie.clientservice.service.ClientService;
import com.businessgenie.clientservice.util.exception.NoClientExistsException;
import com.businessgenie.clientservice.util.exception.ClientAlreadyExistsException;
import com.businessgenie.clientservice.util.exception.ClientNotExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/client")
public class ClientController {
    @Autowired
    ClientService clientService;

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        List<Clients> clients = null;
        try {
            clients = clientService.getAllClients();
            return new ResponseEntity<>(clients, HttpStatus.OK);
        } catch (NoClientExistsException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClient(@PathVariable("id") UUID id) {
        Clients client = null;
        try {
            client = clientService.getClient(id);
            return new ResponseEntity<>(client, HttpStatus.OK);
        } catch (ClientNotExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> createClient(@RequestBody() Clients client){
        try {
            Clients newClient = clientService.createClient(client);
            return new ResponseEntity<>(newClient,HttpStatus.OK);
        } catch (ClientAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> putUserClientAllocation(@RequestBody() Clients client){
        try {
            Clients updatedClient = clientService.updateClient(client);
            return new ResponseEntity<>(updatedClient,HttpStatus.OK);
        } catch (ClientNotExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable("id") UUID id){
        try {
            clientService.deleteClient(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ClientNotExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

