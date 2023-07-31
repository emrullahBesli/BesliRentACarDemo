package com.besliCar.carservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CarCouldNotFoundByIdException extends RuntimeException {
    public CarCouldNotFoundByIdException(String message) {
        super(message);
    }
}
