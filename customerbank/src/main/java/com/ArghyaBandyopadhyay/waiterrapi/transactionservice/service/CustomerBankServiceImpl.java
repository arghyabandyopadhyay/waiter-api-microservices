package com.ArghyaBandyopadhyay.waiterrapi.transactionservice.service;


import com.ArghyaBandyopadhyay.waiterrapi.transactionservice.repository.CustomerBankRepository;
import com.ArghyaBandyopadhyay.waiterrapi.transactionservice.model.Customer;
import com.ArghyaBandyopadhyay.waiterrapi.transactionservice.model.Transaction;
import com.ArghyaBandyopadhyay.waiterrapi.transactionservice.util.exception.CustomerNotExistsException;
import com.ArghyaBandyopadhyay.waiterrapi.transactionservice.util.exception.TransactionAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerBankServiceImpl implements CustomerBankService {

    @Autowired
    CustomerBankRepository customerBankRepository;
    private final RestTemplate restTemplate;
    @Autowired
    public CustomerBankServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public Transaction addTransaction(Transaction transaction) throws TransactionAlreadyExistsException, CustomerNotExistsException {
        Transaction added = null;
        try{
            if(transaction.getId()==null)throw new EntityNotFoundException();
            customerBankRepository.getReferenceById(transaction.getId());
            throw new TransactionAlreadyExistsException();
        }catch(EntityNotFoundException e){
            transaction.setTimeStamp(LocalDateTime.now());
            Customer customer=restTemplate.getForObject("http://WaiterrApiCustomerservice/api/v1/customers/"+transaction.getCustomerId(), Customer.class);
            Customer recipient=restTemplate.getForObject("http://WaiterrApiCustomerservice/api/v1/customers/"+transaction.getRecipient(), Customer.class);
            if(customer!=null&&recipient!=null) {
                transaction.setReceiverCurrentBalance(recipient.getCheckinBalance()+transaction.getAmount());
                added = customerBankRepository.save(transaction);
                customer.setCheckinBalance(added.getCurrentBalance());
                customer.setSavingsBalance(customer.getSavingsBalance() + transaction.getRoundedOffAmount() - transaction.getAmount());
                recipient.setCheckinBalance(recipient.getCheckinBalance()+added.getAmount());
                restTemplate.put("http://WaiterrApiCustomerservice/api/v1/customers/"+recipient.getId(), recipient, Customer.class);
                restTemplate.put("http://WaiterrApiCustomerservice/api/v1/customers/"+customer.getId(), customer, Customer.class);
            }
            else throw new CustomerNotExistsException();
        }
        return added;
    }

    @Override
    public List<Transaction> getAllTransactions(UUID id) {
        return customerBankRepository.findAllTransaction(id.toString());
    }


}
