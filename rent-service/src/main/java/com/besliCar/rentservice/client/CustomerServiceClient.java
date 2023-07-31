package com.besliCar.rentservice.client;

import com.besliCar.rentservice.dto.CreateCustomerRequest;
import com.besliCar.rentservice.dto.CustomerDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "customer-service", path = "/v1/customer")
public interface CustomerServiceClient {
    Logger logger = LoggerFactory.getLogger(CarServiceClient.class);

    @GetMapping("/{name}")
    @CircuitBreaker(name = "getCustomerCircuitBreaker", fallbackMethod = "getCustomerFallback")
    ResponseEntity<CustomerDto> getCustomerByName(@PathVariable String name);

    default ResponseEntity<CustomerDto> getCustomerFallback(Exception exception) {
        logger.info("Failed to getCustomerByName, returning fallback response");
        return ResponseEntity.ok(null);
    }

    @GetMapping("/byId/{id}")
    @CircuitBreaker(name = "getCustomerByIdCircuitBreaker", fallbackMethod = "getCustomerByIdFallback")
    ResponseEntity<CustomerDto> getCustomerById(@PathVariable String id);

    default ResponseEntity<CustomerDto> getCustomerByIdFallback(Exception exception) {
        logger.info("Failed to getCustomerById, returning fallback response");
        return ResponseEntity.ok(null);
    }

    @PostMapping
    @CircuitBreaker(name = "createCustomerCircuitBreaker", fallbackMethod = "createCustomerFallback")
    ResponseEntity<CustomerDto> createCustomer(@RequestBody @Valid CreateCustomerRequest request);

    default ResponseEntity<CustomerDto> createCustomerFallback(Exception exception) {
        logger.info("Failed to create customer, returning fallback response");
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    @CircuitBreaker(name = "updateCustomerCircuitBreaker", fallbackMethod = "updateCustomerRoleToUserFallback")
    void updateCustomerRoleToUser(@PathVariable String id);

    default void updateCustomerRoleToUserFallback(Exception exception) {
        logger.info("Failed to update customer, returning fallback response");
    }

    @PutMapping("/rented/{id}")
    @CircuitBreaker(name = "updateCustomerToRentedCircuitBreaker", fallbackMethod = "updateCustomerToRentedFallback")
    void updateCustomerToRented(@PathVariable String id);

    default void updateCustomerToRentedFallback(Exception exception) {
        logger.info("Failed to update customer to rented, returning fallback response");
    }

    @PutMapping("/idle/{id}")
    @CircuitBreaker(name = "updateCustomerToIdleCircuitBreaker", fallbackMethod = "updateCustomerToIdleFallback")
    void updateCustomerToIdle(@PathVariable String id);

    default void updateCustomerToIdleFallback(Exception exception)  {
        logger.info("Failed to update customer to idle, returning fallback response");
    }

}
