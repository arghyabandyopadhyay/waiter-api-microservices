package com.businessgenie.maxtakeawayservice.controller;

import com.businessgenie.maxtakeawayservice.model.MaxTakeAway;
import com.businessgenie.maxtakeawayservice.service.MaxTakeAwayService;
import com.businessgenie.maxtakeawayservice.util.exception.NoMaxTakeAwayExistsException;
import com.businessgenie.maxtakeawayservice.util.exception.MaxTakeAwayAlreadyExistsException;
import com.businessgenie.maxtakeawayservice.util.exception.MaxTakeAwayNotExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/maxtakeaway")
public class MaxTakeAwayController {
    @Autowired
    MaxTakeAwayService maxTakeAwayService;
    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        List<MaxTakeAway> maxTakeAways = null;
        try {
            maxTakeAways = maxTakeAwayService.getAllMaxTakeAway();
            return new ResponseEntity<>(maxTakeAways, HttpStatus.OK);
        } catch (NoMaxTakeAwayExistsException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NO_CONTENT);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getMaxTakeAway(@PathVariable("id") UUID id) {
        MaxTakeAway maxTakeAway = null;
        try {
            maxTakeAway = maxTakeAwayService.getMaxTakeAway(id);
            return new ResponseEntity<>(maxTakeAway, HttpStatus.OK);
        } catch (MaxTakeAwayNotExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMaxTakeAway(@PathVariable("id") UUID id){
        try {
            maxTakeAwayService.deleteMaxTakeAway(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (MaxTakeAwayNotExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/")
    public ResponseEntity<?> createMaxTakeAway(@RequestBody() MaxTakeAway maxTakeAway){
        try {
            MaxTakeAway newMaxTakeAway = maxTakeAwayService.createMaxTakeAway(maxTakeAway);
            return new ResponseEntity<>(newMaxTakeAway,HttpStatus.OK);
        } catch (MaxTakeAwayAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/")
    public ResponseEntity<?> putUserClientAllocation(@RequestBody() MaxTakeAway maxTakeAway){
        try {
            MaxTakeAway updatedMaxTakeAway = maxTakeAwayService.updateMaxTakeAway(maxTakeAway);
            return new ResponseEntity<>(updatedMaxTakeAway,HttpStatus.OK);
        } catch (MaxTakeAwayNotExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

