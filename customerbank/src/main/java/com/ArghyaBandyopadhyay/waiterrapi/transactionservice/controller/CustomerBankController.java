package com.ArghyaBandyopadhyay.waiterrapi.transactionservice.controller;

import com.ArghyaBandyopadhyay.waiterrapi.transactionservice.model.Transaction;
import com.ArghyaBandyopadhyay.waiterrapi.transactionservice.service.CustomerBankService;
import com.ArghyaBandyopadhyay.waiterrapi.transactionservice.util.exception.CustomerNotExistsException;
import com.ArghyaBandyopadhyay.waiterrapi.transactionservice.util.exception.TransactionAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
//@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("api/v1/payments")
public class CustomerBankController {
    @Autowired
    CustomerBankService customerBankService;

    @GetMapping("/{custId}")
    public ResponseEntity<?> getCustomer(@PathVariable("custId") UUID custId) {
        List<Transaction> txnList = customerBankService.getAllTransactions(custId);
        return new ResponseEntity<List<Transaction>>(txnList, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> addTransaction(@RequestBody() Transaction transaction){
        try {
            Transaction newTransaction = customerBankService.addTransaction(transaction);
            return new ResponseEntity<Transaction>(newTransaction,HttpStatus.CREATED);
        } catch (TransactionAlreadyExistsException | CustomerNotExistsException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }
}

