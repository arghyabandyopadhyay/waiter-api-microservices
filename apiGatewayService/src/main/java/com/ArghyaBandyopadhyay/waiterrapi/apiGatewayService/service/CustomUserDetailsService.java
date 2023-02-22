package com.ArghyaBandyopadhyay.waiterrapi.apiGatewayService.service;

import com.ArghyaBandyopadhyay.waiterrapi.apiGatewayService.model.Customer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    public RestTemplate restTemplate;
    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        try{
            Customer customer = restTemplate.getForObject("http://WaiterrApiCustomerservice/api/v1/customers/byEmailId"+emailId, Customer.class);
            if(customer==null)throw new UsernameNotFoundException("User name not found");
            return new User(customer.getEmailId(),customer.getPassword(),new ArrayList<>());
        }catch (Exception e){
            throw new UsernameNotFoundException("User name not found");
        }
    }
}
