package com.besliCar.rentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RentCouldNotFoundByIdException extends RuntimeException{
    public RentCouldNotFoundByIdException(String message) {
        super(message);
    }
}
