package com.personal.project.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerDoesNotExistException extends RuntimeException {
    public CustomerDoesNotExistException(String s) {
        super(s);
    }
}
