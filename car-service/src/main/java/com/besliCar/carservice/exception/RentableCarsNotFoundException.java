package com.besliCar.carservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RentableCarsNotFoundException extends RuntimeException {
    public RentableCarsNotFoundException(String message) {
        super(message);
    }
}
