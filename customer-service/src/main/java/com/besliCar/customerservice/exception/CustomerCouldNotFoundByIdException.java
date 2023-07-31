package com.besliCar.customerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerCouldNotFoundByIdException extends RuntimeException {
    public CustomerCouldNotFoundByIdException(String message) {
        super(message);
    }
}
