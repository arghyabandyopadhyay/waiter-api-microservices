package com.businessgenie.apiGatewayService.service;

import com.businessgenie.apiGatewayService.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
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
            Users user = restTemplate.getForObject("http://WaiterrUsersService/api/v1/users/search/"+emailId+"/EmailId", Users.class);
            if(user==null)throw new UsernameNotFoundException("User name not found");
            return new User(user.getEmailId(),user.getPassword(),new ArrayList<>());
        }catch (Exception e){
            throw new UsernameNotFoundException("User name not found");
        }
    }
}
