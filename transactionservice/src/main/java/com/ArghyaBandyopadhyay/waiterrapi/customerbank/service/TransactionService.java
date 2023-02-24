package com.ArghyaBandyopadhyay.waiterrapi.customerbank.service;


import com.ArghyaBandyopadhyay.waiterrapi.customerbank.model.Transaction;
import com.ArghyaBandyopadhyay.waiterrapi.customerbank.util.exception.CustomerNotExistsException;
import com.ArghyaBandyopadhyay.waiterrapi.customerbank.util.exception.TransactionAlreadyExistsException;

import java.util.List;
import java.util.UUID;

public interface TransactionService {

    public Transaction addTransaction(Transaction transaction) throws TransactionAlreadyExistsException, CustomerNotExistsException;

    public List<Transaction> getAllTransactions(UUID id);
}
