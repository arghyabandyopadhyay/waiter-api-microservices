package com.businessgenie.orderservice.controller;

import com.businessgenie.orderservice.model.Orders;
import com.businessgenie.orderservice.service.OrderService;
import com.businessgenie.orderservice.util.exception.NoOrderExistException;
import com.businessgenie.orderservice.util.exception.OrderAlreadyExistsException;
import com.businessgenie.orderservice.util.exception.OrderNotExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
// @CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("api/v1/orders")
public class OrderServiceController {
    @Autowired
    OrderService orderService;

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        List<Orders> orders = null;
        try {
            orders = orderService.getAllOrders();
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") UUID id) {
        Orders order = null;
        try {
            order = orderService.getOrder(id);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (OrderNotExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUser(@PathVariable("userId") String userId) {
        List<Orders> userClientAllocations = null;
        try {
            userClientAllocations = orderService.getAllOrdersForUser(userId);
            return new ResponseEntity<>(userClientAllocations, HttpStatus.OK);
        } catch (NoOrderExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") UUID id){
        try {
            orderService.deleteOrder(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (OrderNotExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/")
    public ResponseEntity<?> createOrder(@RequestBody() Orders userClientAllocation){
        try {
            Orders newOrder = orderService.createOrder(userClientAllocation);
            return new ResponseEntity<>(newOrder,HttpStatus.OK);
        } catch (OrderAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateOrder(@RequestBody() Orders userClientAllocation){
        try {
            Orders updatedOrder = orderService.updateOrder(userClientAllocation);
            return new ResponseEntity<>(updatedOrder,HttpStatus.OK);
        } catch (OrderNotExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

