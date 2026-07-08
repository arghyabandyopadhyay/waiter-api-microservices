package com.businessgenie.users.controller;

import com.businessgenie.users.Constants.ContactTypes;
import com.businessgenie.users.model.Login;
import com.businessgenie.users.model.Users;
import com.businessgenie.users.service.UsersService;
import com.businessgenie.users.util.exception.NoUsersExistsException;
import com.businessgenie.users.util.exception.UserAlreadyExistsException;
import com.businessgenie.users.util.exception.UserAuthenticationException;
import com.businessgenie.users.util.exception.UserNotExistsException;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
//@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("api/v1/users")
public class UsersController {
    @Autowired
    UsersService usersService;

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        List<Users> users = null;
        try {
            users = usersService.getAllUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (NoUsersExistsException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable("userId") UUID userId) {
        Users users = null;
        try {
            users = usersService.getUser(userId);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (UserNotExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search/{searchText}")
    public ResponseEntity<?> searchUser(@PathVariable("searchText") String searchText) {
        try {
            return new ResponseEntity<>(usersService.searchUser(searchText), HttpStatus.OK);
        } catch (NoUsersExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search/{searchText}/{searchType}")
    public ResponseEntity<?> searchUser(@PathVariable("searchText") String searchText,@PathVariable("searchType") String searchType) {
        try {
            return new ResponseEntity<>(usersService.searchUser(searchText, ContactTypes.valueOf(searchType)), HttpStatus.OK);
        } catch (UserNotExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody() Login login){
        try {
            Users users = usersService.authenticate(login);
            return new ResponseEntity<>(users,HttpStatus.OK);
        } catch (UserAuthenticationException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
        }catch (UserNotExistsException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody() Users user){
        try {
            Users createdUser = usersService.createUser(user);
            return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") UUID userId){
        try {
            usersService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UserNotExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> putUser(@RequestBody() Users user){
        try {
            Users updatedUser = usersService.updateUser(user);
            return new ResponseEntity<>(updatedUser,HttpStatus.OK);
        } catch (UserNotExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

