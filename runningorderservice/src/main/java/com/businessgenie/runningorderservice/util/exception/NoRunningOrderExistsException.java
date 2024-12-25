package com.businessgenie.runningorderservice.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "User with specified details not found")
public class NoRunningOrderExistsException extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
}
