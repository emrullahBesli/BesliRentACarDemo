package com.besliCar.customerservice.controller;

import com.besliCar.customerservice.dto.CreateCustomerRequest;
import com.besliCar.customerservice.dto.CustomerDto;
import com.besliCar.customerservice.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable String id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @GetMapping("/{name}")
    public ResponseEntity<CustomerDto> getCustomerByName(@PathVariable String name) {
        return ResponseEntity.ok(customerService.getCustomerByName(name));
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody @Valid CreateCustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PutMapping("/{id}")
    public void updateCustomerRoleToUser(@PathVariable String id) {
        customerService.updateCustomerRoleToUser(id);
    }

    @PutMapping("/rented/{id}")
    public void updateCustomerToRented(@PathVariable String id){
         customerService.updateCustomerStatusToRented(id);
    }
    @PutMapping("/idle/{id}")
    public void updateCustomerToIdle(@PathVariable String id){
        customerService.updateCustomerStatusToIdle(id);
    }

}
