package com.besliCar.rentservice.client;

import com.besliCar.rentservice.dto.CarDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


import java.util.Collections;
import java.util.List;


@FeignClient(name = "car-service", path = "/v1/car")
public interface CarServiceClient {
    Logger logger = LoggerFactory.getLogger(CarServiceClient.class);

    @GetMapping()
    @CircuitBreaker(name = "getAllCarsRentableCircuitBreaker", fallbackMethod = "getCarFallback")
    ResponseEntity<List<CarDto>> getAllCarsRentable();
    default ResponseEntity<List<CarDto>> getCarFallback(Exception exception) {
        logger.info("Failed to get cars, returning fallback response");
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("/{id}")
    @CircuitBreaker(name = "getAllCarsRentableCircuitBreaker", fallbackMethod = "getCarByIdFallback")
    ResponseEntity<CarDto> getCarById(@PathVariable String id);
    default ResponseEntity<CarDto> getCarByIdFallback(Exception exception) {
        logger.info("Failed to get Car By id, returning fallback response");
        return ResponseEntity.ok(null);
    }

    @PutMapping("/rentable/{id}")
    @CircuitBreaker(name = "updateCarStatusToRentableCircuitBreaker", fallbackMethod = "updateCarStatusToRentableFallback")
    void updateCarStatusToRentable(@PathVariable String id);
    default void updateCarStatusToRentableFallback(Exception exception) {
        logger.info("Failed to update car status to rentable, returning fallback response");
    }

    @PutMapping("/unrentable/{id}")
    @CircuitBreaker(name = "updateCarStatusToUnrentableCircuitBreaker", fallbackMethod = "updateCarStatusToUnrentableFallback")
    void updateCarStatusToUnrentable(@PathVariable String id);
    default void updateCarStatusToUnrentableFallback(Exception exception) {
        logger.info("Failed to update car status to unrentable, returning fallback response",exception);
    }

}
