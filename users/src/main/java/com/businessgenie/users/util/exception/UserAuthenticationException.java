package com.businessgenie.users.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN,reason = "User login details found incorrect.")
public class UserAuthenticationException extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
}
