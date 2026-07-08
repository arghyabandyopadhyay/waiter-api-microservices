package com.businessgenie.taxclassservice.controller;

import com.businessgenie.taxclassservice.model.TaxClass;
import com.businessgenie.taxclassservice.service.TaxClassService;
import com.businessgenie.taxclassservice.util.exception.NoTaxClassExistsException;
import com.businessgenie.taxclassservice.util.exception.TaxClassAlreadyExistsException;
import com.businessgenie.taxclassservice.util.exception.TaxClassNotExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/taxclass")
public class TaxClassController {
    @Autowired
    TaxClassService taxClassService;

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        List<TaxClass> taxClasses = null;
        try {
            taxClasses = taxClassService.getAllTaxClasses();
            return new ResponseEntity<>(taxClasses, HttpStatus.OK);
        } catch (NoTaxClassExistsException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTaxClasses(@PathVariable("id") UUID id) {
        TaxClass taxClass = null;
        try {
            taxClass = taxClassService.getTaxClass(id);
            return new ResponseEntity<>(taxClass, HttpStatus.OK);
        } catch (TaxClassNotExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<?> getAllTaxClassesForClient(@PathVariable("clientId") String clientId) {
        List<TaxClass> taxClasses = null;
        try {
            taxClasses = taxClassService.getAllTaxClassForClient(clientId);
            return new ResponseEntity<>(taxClasses, HttpStatus.OK);
        } catch (NoTaxClassExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTaxClass(@PathVariable("id") UUID id){
        try {
            taxClassService.deleteTaxClass(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (TaxClassNotExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/")
    public ResponseEntity<?> createTaxClass(@RequestBody() TaxClass userClientAllocation){
        try {
            TaxClass newUserClientAllocation = taxClassService.createTaxClass(userClientAllocation);
            return new ResponseEntity<>(newUserClientAllocation,HttpStatus.OK);
        } catch (TaxClassAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> updateTaxClass(@RequestBody() TaxClass userClientAllocation){
        try {
            TaxClass upldatedUserClientAllocation = taxClassService.updateTaxClass(userClientAllocation);
            return new ResponseEntity<>(upldatedUserClientAllocation,HttpStatus.OK);
        } catch (TaxClassNotExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

