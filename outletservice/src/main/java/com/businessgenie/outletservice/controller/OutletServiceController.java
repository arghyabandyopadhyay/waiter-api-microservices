package com.businessgenie.outletservice.controller;

import com.businessgenie.outletservice.model.Outlets;
import com.businessgenie.outletservice.service.OutletService;
import com.businessgenie.outletservice.util.exception.NoOutletExistsException;
import com.businessgenie.outletservice.util.exception.OutletAlreadyExistsException;
import com.businessgenie.outletservice.util.exception.OutletNotExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/outlets")
public class OutletServiceController {
    @Autowired
    OutletService outletService;

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        List<Outlets> outlets = null;
        try {
            outlets = outletService.getAllOutlets();
            return new ResponseEntity<>(outlets, HttpStatus.OK);
        } catch (NoOutletExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOutlet(@PathVariable("id") UUID id) {
        Outlets outlet = null;
        try {
            outlet = outletService.getOutlet(id);
            return new ResponseEntity<>(outlet, HttpStatus.OK);
        } catch (OutletNotExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<?> getOutletsForClient(@PathVariable("clientId") String clientId) {
        List<Outlets> outlets = null;
        try {
            outlets = outletService.getAllOutletsForClient(clientId);
            return new ResponseEntity<>(outlets, HttpStatus.OK);
        } catch (NoOutletExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOutlet(@PathVariable("id") UUID id){
        try {
            outletService.deleteOutlet(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (OutletNotExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/")
    public ResponseEntity<?> createOutlet(@RequestBody() Outlets userClientAllocation){
        try {
            Outlets newUserClientAllocation = outletService.createOutlet(userClientAllocation);
            return new ResponseEntity<>(newUserClientAllocation,HttpStatus.OK);
        } catch (OutletAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> updateOutlet(@RequestBody() Outlets userClientAllocation){
        try {
            Outlets upldatedUserClientAllocation = outletService.updateOutlet(userClientAllocation);
            return new ResponseEntity<>(upldatedUserClientAllocation,HttpStatus.OK);
        } catch (OutletNotExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

