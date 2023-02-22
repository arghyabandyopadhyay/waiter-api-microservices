package com.ArghyaBandyopadhyay.waiterrapi.authservice.service;

import com.ArghyaBandyopadhyay.waiterrapi.authservice.model.Customer;
import com.ArghyaBandyopadhyay.waiterrapi.authservice.util.JwtUtil;
import com.ArghyaBandyopadhyay.waiterrapi.authservice.util.exception.CustomerAlreadyExistsException;
import com.ArghyaBandyopadhyay.waiterrapi.authservice.util.exception.CustomerNotExistsException;
import com.ArghyaBandyopadhyay.waiterrapi.authservice.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    private final RestTemplate restTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    public AuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Customer register(Customer customerRequest) throws CustomerAlreadyExistsException {
        //do validation if user already exists
        try{
            customerRequest.setPassword(BCrypt.hashpw(customerRequest.getPassword(), BCrypt.gensalt()));
            Customer customer = restTemplate.postForObject("http://WaiterrApiCustomerservice/api/v1/customers/register", customerRequest, Customer.class);
            if(customer==null)throw new CustomerAlreadyExistsException();
            return customer;
        }catch (Exception e){
            throw new CustomerAlreadyExistsException();
        }
    }

    public Customer login(Login login) throws CustomerNotExistsException {
        //do validation if user already exists
        try{
            Customer customer = restTemplate.postForObject("http://WaiterrApiCustomerservice/api/v1/customers/authenticate", login, Customer.class);
            if(customer==null)throw new CustomerNotExistsException();
            customer.setJwtToken(jwtUtil.generateToken(login.getEmail()));
            return customer;
        }catch (Exception e){
            throw new CustomerNotExistsException();
        }

    }
}
