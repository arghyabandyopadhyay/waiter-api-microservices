package com.ArghyaBandyopadhyay.waiterrapi.commentforkotsuggestion.controller;

import com.ArghyaBandyopadhyay.waiterrapi.commentforkotsuggestion.model.Transaction;
import com.ArghyaBandyopadhyay.waiterrapi.commentforkotsuggestion.service.TransactionService;
import com.ArghyaBandyopadhyay.waiterrapi.commentforkotsuggestion.util.exception.CustomerNotExistsException;
import com.ArghyaBandyopadhyay.waiterrapi.commentforkotsuggestion.util.exception.TransactionAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
//@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("api/v1/waiterr/payments")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @GetMapping("/{custId}")
    public ResponseEntity<?> getCustomer(@PathVariable("custId") UUID custId) {
        List<Transaction> txnList = transactionService.getAllTransactions(custId);
        return new ResponseEntity<List<Transaction>>(txnList, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> addTransaction(@RequestBody() Transaction transaction){
        try {
            Transaction newTransaction = transactionService.addTransaction(transaction);
            return new ResponseEntity<Transaction>(newTransaction,HttpStatus.CREATED);
        } catch (TransactionAlreadyExistsException | CustomerNotExistsException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }
}

