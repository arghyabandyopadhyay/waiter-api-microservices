package com.businessgenie.clientservice.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Client with specified details not found")
public class NoClientExistsException extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
}
