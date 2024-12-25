package com.businessgenie.taxclassservice.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Tax class with specified details not found")
public class NoTaxClassExistsException extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
}
