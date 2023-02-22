package com.ArghyaBandyopadhyay.waiterrapi.transactionservice.service;


import com.ArghyaBandyopadhyay.waiterrapi.transactionservice.model.Transaction;
import com.ArghyaBandyopadhyay.waiterrapi.transactionservice.util.exception.CustomerNotExistsException;
import com.ArghyaBandyopadhyay.waiterrapi.transactionservice.util.exception.TransactionAlreadyExistsException;

import java.util.List;
import java.util.UUID;

public interface TransactionService {

    public Transaction addTransaction(Transaction transaction) throws TransactionAlreadyExistsException, CustomerNotExistsException;

    public List<Transaction> getAllTransactions(UUID id);
}
