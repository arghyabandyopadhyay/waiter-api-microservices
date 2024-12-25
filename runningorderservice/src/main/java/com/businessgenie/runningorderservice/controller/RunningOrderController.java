package com.businessgenie.runningorderservice.controller;

import com.businessgenie.runningorderservice.model.RunningOrder;
import com.businessgenie.runningorderservice.service.RunningOrderService;
import com.businessgenie.runningorderservice.util.exception.NoRunningOrderExistsException;
import com.businessgenie.runningorderservice.util.exception.RunningOrderAlreadyExistsException;
import com.businessgenie.runningorderservice.util.exception.RunningOrderNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
// @CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("api/v1/runningorders")
public class RunningOrderController {
    @Autowired
    RunningOrderService runningOrderService;

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        List<RunningOrder> runningOrders = null;
        try {
            runningOrders = runningOrderService.getAllRunningOrders();
            return new ResponseEntity<>(runningOrders, HttpStatus.OK);
        } catch (NoRunningOrderExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getRunningOrdersForUser(@PathVariable("userId") String userId) {
        List<RunningOrder> runningOrders = null;
        try {
            runningOrders = runningOrderService.getAllRunningOrdersForUser(userId);
            return new ResponseEntity<>(runningOrders, HttpStatus.OK);
        } catch (NoRunningOrderExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRunningOrder(@PathVariable("id") UUID id) {
        RunningOrder runningOrder = null;
        try {
            runningOrder = runningOrderService.getRunningOrder(id);
            return new ResponseEntity<>(runningOrder, HttpStatus.OK);
        } catch (RunningOrderNotExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRunningOrder(@PathVariable("id") UUID id){
        try {
            runningOrderService.deleteRunningOrder(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RunningOrderNotExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/")
    public ResponseEntity<?> createRunningOrder(@RequestBody() RunningOrder userClientAllocation){
        try {
            RunningOrder newRunningOrder = runningOrderService.createRunningOrder(userClientAllocation);
            return new ResponseEntity<>(newRunningOrder,HttpStatus.OK);
        } catch (RunningOrderAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> updateRunningOrder(@RequestBody() RunningOrder userClientAllocation){
        try {
            RunningOrder updatedRunningOrder = runningOrderService.updateRunningOrder(userClientAllocation);
            return new ResponseEntity<>(updatedRunningOrder,HttpStatus.OK);
        } catch (RunningOrderNotExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

