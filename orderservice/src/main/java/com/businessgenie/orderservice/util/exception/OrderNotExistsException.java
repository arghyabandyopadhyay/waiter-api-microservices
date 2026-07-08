package com.businessgenie.orderservice.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Order with specified details not found")
public class OrderNotExistsException extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
}
