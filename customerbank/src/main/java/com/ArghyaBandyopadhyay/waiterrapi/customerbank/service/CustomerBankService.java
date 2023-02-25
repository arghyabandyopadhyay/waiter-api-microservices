package com.ArghyaBandyopadhyay.waiterrapi.customerbank.service;


import com.ArghyaBandyopadhyay.waiterrapi.customerbank.model.CustomerDetails;
import com.ArghyaBandyopadhyay.waiterrapi.customerbank.util.exception.CustomerAlreadyExistsException;
import com.ArghyaBandyopadhyay.waiterrapi.customerbank.util.exception.CustomerNotExistsException;

import java.util.UUID;

public interface CustomerBankService {

    public CustomerDetails addCustomerDetail(CustomerDetails customerDetails) throws CustomerAlreadyExistsException;
    public void updateCustomerDetail(CustomerDetails customerDetails) throws CustomerNotExistsException;
    public void deleteCustomerDetail(UUID id) throws CustomerNotExistsException;
    public CustomerDetails getCustomerDetail(UUID id) throws CustomerNotExistsException;
    public CustomerDetails getCustomerDetailUsingMobileNo(String mobileNo)throws CustomerNotExistsException;
}
