package com.besliCar.rentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CarCannotBeRentedException extends RuntimeException{
    public CarCannotBeRentedException(String message) {
        super(message);
    }
}
