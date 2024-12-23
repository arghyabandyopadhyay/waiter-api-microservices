package com.businessgenie.userclientallocations.controller;

import com.businessgenie.userclientallocations.model.UserClientAllocation;
import com.businessgenie.userclientallocations.service.UserClientAllocationsService;
import com.businessgenie.userclientallocations.util.exception.NoUserClientAllocationExistsException;
import com.businessgenie.userclientallocations.util.exception.UserClientAllocationNotExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
// @CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("api/v1/userclientallocations")
public class UserClientAllocationsController {
    @Autowired
    UserClientAllocationsService userClientAllocationsService;

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        List<UserClientAllocation> users = null;
        try {
            users = userClientAllocationsService.getAllUserClientAllocations();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (NoUserClientAllocationExistsException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUser(@PathVariable("userId") String userId) {
        List<UserClientAllocation> userClientAllocations = null;
        try {
            userClientAllocations = userClientAllocationsService.getAllUserClientAllocationForUser(userId);
            return new ResponseEntity<>(userClientAllocations, HttpStatus.OK);
        } catch (NoUserClientAllocationExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") UUID id){
        try {
            userClientAllocationsService.deleteUserClientAllocation(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UserClientAllocationNotExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> putUser(@RequestBody() UserClientAllocation userClientAllocation){
        try {
            UserClientAllocation upldatedUserClientAllocation = userClientAllocationsService.updateUserClientAllocation(userClientAllocation);
            return new ResponseEntity<>(upldatedUserClientAllocation,HttpStatus.OK);
        } catch (UserClientAllocationNotExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

