package com.businessgenie.menugroupservice.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Menu group with specified details not found")
public class NoMenuGroupExistsException extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
}
