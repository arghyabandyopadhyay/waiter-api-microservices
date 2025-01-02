package com.businessgenie.menugroupservice.controller;

import com.businessgenie.menugroupservice.dto.MenuGroupDTO;
import com.businessgenie.menugroupservice.model.MenuGroup;
import com.businessgenie.menugroupservice.service.MenuGroupService;
import com.businessgenie.menugroupservice.util.exception.NoMenuGroupExistsException;
import com.businessgenie.menugroupservice.util.exception.MenuGroupAlreadyExistsException;
import com.businessgenie.menugroupservice.util.exception.MenuGroupNotExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/menugroup")
public class MenuGroupController {
    @Autowired
    MenuGroupService menuGroupService;

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        List<MenuGroup> users = null;
        try {
            users = menuGroupService.getAllMenuGroup();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (NoMenuGroupExistsException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") UUID id) {
        MenuGroup menuGroup = null;
        try {
            menuGroup = menuGroupService.getMenuGroup(id);
            return new ResponseEntity<>(menuGroup, HttpStatus.OK);
        } catch (MenuGroupNotExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/outlet/{outletId}")
    public ResponseEntity<?> getByOutletId(@PathVariable("outletId") String outletId) {
        List<MenuGroupDTO> menuGroups = null;
        try {
            menuGroups = menuGroupService.getAllMenuGroupForOutlet(outletId);
            return new ResponseEntity<>(menuGroups, HttpStatus.OK);
        } catch (NoMenuGroupExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<?> getByClientId(@PathVariable("clientId") String clientId) {
        List<MenuGroupDTO> menuGroups = null;
        try {
            menuGroups = menuGroupService.getAllMenuGroupForClient(clientId);
            return new ResponseEntity<>(menuGroups, HttpStatus.OK);
        } catch (NoMenuGroupExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMenuGroup(@PathVariable("id") UUID id){
        try {
            menuGroupService.deleteMenuGroup(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (MenuGroupNotExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/")
    public ResponseEntity<?> createMenuGroup(@RequestBody() MenuGroup menuGroup){
        try {
            MenuGroup newMenuGroup = menuGroupService.createMenuGroup(menuGroup);
            return new ResponseEntity<>(newMenuGroup,HttpStatus.OK);
        } catch (MenuGroupAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<?> putUserClientAllocation(@RequestBody() MenuGroup menuGroup){
        try {
            MenuGroup updatedMenuGroup = menuGroupService.updateMenuGroup(menuGroup);
            return new ResponseEntity<>(updatedMenuGroup,HttpStatus.OK);
        } catch (MenuGroupNotExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

