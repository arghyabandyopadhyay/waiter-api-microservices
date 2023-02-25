package com.ArghyaBandyopadhyay.waiterrapi.commentforkotsuggestion.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Customer with specified details not found")
public class CommentForKOTSuggestionNotExistsException extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
}
