package com.businessgenie.maxtakeawayservice.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Max take away with specified details not found")
public class MaxTakeAwayNotExistsException extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
}
