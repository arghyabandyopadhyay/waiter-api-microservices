package com.businessgenie.authservice.service;

import com.businessgenie.authservice.model.Login;
import com.businessgenie.authservice.model.Users;
import com.businessgenie.authservice.util.JwtUtil;
import com.businessgenie.authservice.util.exception.CustomerAlreadyExistsException;
import com.businessgenie.authservice.util.exception.CustomerNotExistsException;
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

    public Users register(Users userRequest) throws CustomerAlreadyExistsException {
        //do validation if user already exists
        try{
            userRequest.setPassword(BCrypt.hashpw(userRequest.getPassword(), BCrypt.gensalt()));
            Users users = restTemplate.postForObject("http://WaiterrUsersService/api/v1/users/register", userRequest, Users.class);
            if(users==null)throw new CustomerAlreadyExistsException();
            return users;
        }catch (Exception e){
            throw new CustomerAlreadyExistsException();
        }
    }

    public Users login(Login login) throws CustomerNotExistsException {
        //do validation if user already exists
        try{
            Users user = restTemplate.postForObject("http://WaiterrUsersService/api/v1/users/authenticate", login, Users.class);
            if(user==null)throw new CustomerNotExistsException();
            user.setJwtToken(jwtUtil.generateToken(login.getEmailId()));
            return user;
        }catch (Exception e){
            throw new CustomerNotExistsException();
        }

    }
}
