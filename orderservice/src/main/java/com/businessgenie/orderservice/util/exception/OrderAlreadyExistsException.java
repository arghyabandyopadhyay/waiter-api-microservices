package com.businessgenie.orderservice.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(code = HttpStatus.CONFLICT,reason = "Order already exists")
public class OrderAlreadyExistsException extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
}
