package com.businessgenie.maxtakeawayservice.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(code = HttpStatus.CONFLICT,reason = "Max take away already exists")
public class MaxTakeAwayAlreadyExistsException extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
}
