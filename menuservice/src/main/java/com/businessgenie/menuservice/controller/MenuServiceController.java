package com.businessgenie.menuservice.controller;

import com.businessgenie.menuservice.dto.MenuItemDTO;
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
public class MenuServiceController {
    @Autowired
    MenuItemService menuItemService;

    @GetMapping("/")
    public ResponseEntity<?> getAllMenu() {
        List<MenuItem> users = null;
        try {
            users = menuItemService.getAllMenuItems();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (NoMenuItemExistsException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/outlet/{outletId}")
    public ResponseEntity<?> getMenuForWaiter(@PathVariable("outletId") String outletId) {
        List<MenuItemDTO> menuItems = null;
        try {
            menuItems = menuItemService.getAllMenuItemForOutlet(outletId);
            return new ResponseEntity<>(menuItems, HttpStatus.OK);
        } catch (NoMenuItemExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<?> getMenuForClient(@PathVariable("clientId") String clientId) {
        List<MenuItemDTO> menuItems = null;
        try {
            menuItems = menuItemService.getAllMenuItemForClient(clientId);
            return new ResponseEntity<>(menuItems, HttpStatus.OK);
        } catch (NoMenuItemExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/outlet/{outletId}/user/{userId}")
    public ResponseEntity<?> getMenuForUser(@PathVariable("outletId")String outletId, @PathVariable("userId") String userId) {
        List<MenuItemDTO> menuItems = null;
        try {
            menuItems = menuItemService.getAllMenuItemForUser(outletId,userId);
            return new ResponseEntity<>(menuItems, HttpStatus.OK);
        } catch (NoMenuItemExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMenu(@PathVariable("id") UUID id){
        try {
            menuItemService.deleteMenuItem(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (MenuItemNotExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/")
    public ResponseEntity<?> createMenuItem(@RequestBody() MenuItem menuItem){
        try {
            MenuItem newMenuItem = menuItemService.createMenuItem(menuItem);
            return new ResponseEntity<>(newMenuItem,HttpStatus.OK);
        } catch (MenuItemAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> putMenuItem(@RequestBody() MenuItem menuItem){
        try {
            MenuItem updatedMenuItem = menuItemService.updateMenuItem(menuItem);
            return new ResponseEntity<>(updatedMenuItem,HttpStatus.OK);
        } catch (MenuItemNotExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

