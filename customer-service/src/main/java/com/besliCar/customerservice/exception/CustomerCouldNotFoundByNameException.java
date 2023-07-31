package com.besliCar.customerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerCouldNotFoundByNameException extends RuntimeException {
    public CustomerCouldNotFoundByNameException(String message) {
        super(message);
    }
}
