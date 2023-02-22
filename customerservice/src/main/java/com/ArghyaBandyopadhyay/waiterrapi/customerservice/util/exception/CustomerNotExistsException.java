package com.ArghyaBandyopadhyay.waiterrapi.customerservice.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Customer with specified details not found")
public class CustomerNotExistsException extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
}
