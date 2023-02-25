package com.ArghyaBandyopadhyay.waiterrapi.customerbank.controller;

import com.ArghyaBandyopadhyay.waiterrapi.customerbank.model.CustomerDetails;
import com.ArghyaBandyopadhyay.waiterrapi.customerbank.service.CustomerBankService;
import com.ArghyaBandyopadhyay.waiterrapi.customerbank.util.exception.CustomerAlreadyExistsException;
import com.ArghyaBandyopadhyay.waiterrapi.customerbank.util.exception.CustomerNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/waiterr/customerbank")
public class CustomerBankController {
    @Autowired
    CustomerBankService customerBankService;

    @GetMapping()
    public ResponseEntity<?> getCustomerDetails(@RequestParam(name="identifier",required = false) UUID id, @RequestParam(name="mobile", required = false) String mobileNo) {
        CustomerDetails customerDetail = null;
        try {
            if(id==null && mobileNo==null)throw new CustomerNotExistsException();
            customerDetail = id!=null?customerBankService.getCustomerDetail(id):customerBankService.getCustomerDetailUsingMobileNo(mobileNo);
            return new ResponseEntity<>(customerDetail, HttpStatus.OK);
        } catch (CustomerNotExistsException e) {
            return new ResponseEntity<>("Customer with specified details not found",HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<?> addCustomerDetails(@RequestBody() CustomerDetails customerDetails){
        try {
            CustomerDetails newCustomerDetails = customerBankService.addCustomerDetail(customerDetails);
            return new ResponseEntity<>(newCustomerDetails,HttpStatus.CREATED);
        } catch (CustomerAlreadyExistsException e) {
            return new ResponseEntity<>("Customer already exists",HttpStatus.CONFLICT);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    public ResponseEntity<?> updateCustomerDetails(@RequestBody() CustomerDetails customerDetails){
        try {
            customerBankService.updateCustomerDetail(customerDetails);
            return new ResponseEntity<>("User Details updated successfully",HttpStatus.OK);
        } catch (CustomerNotExistsException e) {
            return new ResponseEntity<>("Customer with specified details not found",HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteCustomerDetails(@RequestParam(name="identifier") UUID id){
        try {
            customerBankService.deleteCustomerDetail(id);
            return new ResponseEntity<>("User detail deleted successfully",HttpStatus.OK);
        } catch (CustomerNotExistsException e) {
            return new ResponseEntity<>("Customer with specified details not found",HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

