package com.businessgenie.authservice.controller;

import com.businessgenie.authservice.model.Login;
import com.businessgenie.authservice.model.Users;
import com.businessgenie.authservice.service.AuthService;
import com.businessgenie.authservice.util.exception.CustomerAlreadyExistsException;
import com.businessgenie.authservice.util.exception.CustomerNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("api/v1/authentication")
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
    public ResponseEntity<?> addCustomer(@RequestBody() Users user){
        try {
            Users addedUser = authService.register(user);
            return new ResponseEntity<>(addedUser,HttpStatus.CREATED);
        } catch (CustomerAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody()Login login) throws Exception{
        try {
            Users authenticatedUser = authService.login(login);
            return new ResponseEntity<>(authenticatedUser,HttpStatus.OK);
        } catch (CustomerNotExistsException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
