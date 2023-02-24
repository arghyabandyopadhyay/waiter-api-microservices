package com.ArghyaBandyopadhyay.waiterrapi.customerbank.service;


import com.ArghyaBandyopadhyay.waiterrapi.customerbank.model.CustomerDetails;
import com.ArghyaBandyopadhyay.waiterrapi.customerbank.repository.CustomerBankRepository;
import com.ArghyaBandyopadhyay.waiterrapi.customerbank.util.exception.CustomerAlreadyExistsException;
import com.ArghyaBandyopadhyay.waiterrapi.customerbank.util.exception.CustomerNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerBankServiceImplementation implements CustomerBankService {

    @Autowired
    CustomerBankRepository customerBankRepository;
    private final RestTemplate restTemplate;
    @Autowired
    public CustomerBankServiceImplementation(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public CustomerDetails addCustomerDetail(CustomerDetails customerDetails) throws CustomerAlreadyExistsException{
        CustomerDetails added = null;
        try{
            if(customerDetails.getId()==null)throw new EntityNotFoundException();
            customerBankRepository.getReferenceById(customerDetails.getId());
            throw new CustomerAlreadyExistsException();
        }catch(EntityNotFoundException e){
            added = customerBankRepository.save(customerDetails);
        }
        return added;
    }

    @Override
    public void updateCustomerDetail(CustomerDetails customerDetails) throws CustomerNotExistsException {
        Optional<CustomerDetails> optionalCustomerDetails=customerBankRepository.findById(customerDetails.getId());
        if(optionalCustomerDetails.isPresent()){
            customerBankRepository.save(customerDetails);
        }
        else throw new CustomerNotExistsException();
    }

    @Override
    public void deleteCustomerDetail(UUID id) throws CustomerNotExistsException {
        Optional<CustomerDetails> optionalCustomerDetails=customerBankRepository.findById(id);
        if(optionalCustomerDetails.isPresent()){
            customerBankRepository.delete(optionalCustomerDetails.get());
        }
        else throw new CustomerNotExistsException();
    }

    @Override
    public CustomerDetails getCustomerDetail(UUID id) throws CustomerNotExistsException {
        Optional<CustomerDetails> optionalCustomerDetails= customerBankRepository.findById(id);
        if(optionalCustomerDetails.isPresent()) return optionalCustomerDetails.get();
        else throw new CustomerNotExistsException();
    }

    @Override
    public CustomerDetails getCustomerDetailUsingMobileNo(String mobileNo) throws CustomerNotExistsException{
        Optional<CustomerDetails> optionalCustomerDetails= customerBankRepository.findByMobileNumber(mobileNo);
        if(optionalCustomerDetails.isPresent()) return optionalCustomerDetails.get();
        else throw new CustomerNotExistsException();
    }


}
