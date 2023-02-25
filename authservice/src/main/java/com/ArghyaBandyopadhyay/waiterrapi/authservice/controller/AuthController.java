package com.ArghyaBandyopadhyay.waiterrapi.authservice.controller;

import com.ArghyaBandyopadhyay.waiterrapi.authservice.util.exception.CustomerAlreadyExistsException;
import com.ArghyaBandyopadhyay.waiterrapi.authservice.model.Customer;
import com.ArghyaBandyopadhyay.waiterrapi.authservice.model.Login;
import com.ArghyaBandyopadhyay.waiterrapi.authservice.service.AuthService;
import com.ArghyaBandyopadhyay.waiterrapi.authservice.util.exception.CustomerNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("api/v1/waiterr/authentication")
public class AuthController {
    /*
     * Autowiring should be implemented for the NewsService. Please note that we
     * should not create any object using the new keyword
     */
    @Autowired
    AuthService authService;
    @Autowired
    public AuthController(final AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> addCustomer(@RequestBody() Customer customer){
        try {
            Customer addedCustomer = authService.register(customer);
            return new ResponseEntity<Customer>(addedCustomer,HttpStatus.CREATED);
        } catch (CustomerAlreadyExistsException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody()Login login) throws Exception{
        try {
            Customer addedCustomer = authService.login(login);
            return new ResponseEntity<Customer>(addedCustomer,HttpStatus.CREATED);
        } catch (CustomerNotExistsException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

}
