package com.ArghyaBandyopadhyay.waiterrapi.commentforkotsuggestion.service;


import com.ArghyaBandyopadhyay.waiterrapi.commentforkotsuggestion.model.Transaction;
import com.ArghyaBandyopadhyay.waiterrapi.commentforkotsuggestion.util.exception.CustomerNotExistsException;
import com.ArghyaBandyopadhyay.waiterrapi.commentforkotsuggestion.util.exception.TransactionAlreadyExistsException;

import java.util.List;
import java.util.UUID;

public interface TransactionService {

    public Transaction addTransaction(Transaction transaction) throws TransactionAlreadyExistsException, CustomerNotExistsException;

    public List<Transaction> getAllTransactions(UUID id);
}
