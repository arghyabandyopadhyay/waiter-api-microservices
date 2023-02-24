package com.ArghyaBandyopadhyay.waiterrapi.customerbank.controller;

import com.ArghyaBandyopadhyay.waiterrapi.customerbank.model.CustomerDetails;
import com.ArghyaBandyopadhyay.waiterrapi.customerbank.service.CustomerBankService;
import com.ArghyaBandyopadhyay.waiterrapi.customerbank.util.exception.CustomerAlreadyExistsException;
import com.ArghyaBandyopadhyay.waiterrapi.customerbank.util.exception.CustomerNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
//@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("api/v1/payments")
public class CustomerBankController {
    @Autowired
    CustomerBankService customerBankService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerDetails(@PathVariable ("id") UUID id) {
        CustomerDetails customerDetail = null;
        try {
            customerDetail = customerBankService.getCustomerDetail(id);
            return new ResponseEntity<>(customerDetail, HttpStatus.OK);
        } catch (CustomerNotExistsException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/")
    public ResponseEntity<?> getCustomerDetailsUsingMobile(@RequestParam("mobileNo") String mobileNo) {
        CustomerDetails customerDetail = null;
        try {
            customerDetail = customerBankService.getCustomerDetailUsingMobileNo(mobileNo);
            return new ResponseEntity<>(customerDetail, HttpStatus.OK);
        } catch (CustomerNotExistsException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<?> addCustomerDetails(@RequestBody() CustomerDetails customerDetails){
        try {
            CustomerDetails newCustomerDetails = customerBankService.addCustomerDetail(customerDetails);
            return new ResponseEntity<CustomerDetails>(newCustomerDetails,HttpStatus.CREATED);
        } catch (CustomerAlreadyExistsException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    public ResponseEntity<?> updateCustomerDetails(@RequestBody() CustomerDetails customerDetails){
        try {
            customerBankService.updateCustomerDetail(customerDetails);
            return new ResponseEntity<String>("User Details updated successfully",HttpStatus.OK);
        } catch (CustomerNotExistsException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomerDetails(@PathVariable("id") UUID id){
        try {
            customerBankService.deleteCustomerDetail(id);
            return new ResponseEntity<String>("User detail deleted successfully",HttpStatus.OK);
        } catch (CustomerNotExistsException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

