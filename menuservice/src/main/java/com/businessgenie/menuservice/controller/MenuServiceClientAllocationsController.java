package com.businessgenie.menuservice.controller;

import com.businessgenie.menuservice.model.MenuItem;
import com.businessgenie.menuservice.service.MenuItemService;
import com.businessgenie.menuservice.util.exception.NoMenuItemExistsException;
import com.businessgenie.menuservice.util.exception.MenuItemAlreadyExistsException;
import com.businessgenie.menuservice.util.exception.MenuItemNotExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/menu")
public class MenuServiceClientAllocationsController {
    @Autowired
    MenuItemService userClientAllocationsService;

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        List<MenuItem> users = null;
        try {
            users = userClientAllocationsService.getAllMenuItems();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (NoMenuItemExistsException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUser(@PathVariable("userId") String userId) {
        List<MenuItem> userClientAllocations = null;
        try {
            userClientAllocations = userClientAllocationsService.getAllMenuItemForOutlet(userId);
            return new ResponseEntity<>(userClientAllocations, HttpStatus.OK);
        } catch (NoMenuItemExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") UUID id){
        try {
            userClientAllocationsService.deleteMenuItem(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (MenuItemNotExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/")
    public ResponseEntity<?> createUserClientAllocation(@RequestBody() MenuItem userClientAllocation){
        try {
            MenuItem newUserClientAllocation = userClientAllocationsService.createMenuItem(userClientAllocation);
            return new ResponseEntity<>(newUserClientAllocation,HttpStatus.OK);
        } catch (MenuItemAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> putUserClientAllocation(@RequestBody() MenuItem userClientAllocation){
        try {
            MenuItem upldatedUserClientAllocation = userClientAllocationsService.updateMenuItem(userClientAllocation);
            return new ResponseEntity<>(upldatedUserClientAllocation,HttpStatus.OK);
        } catch (MenuItemNotExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

