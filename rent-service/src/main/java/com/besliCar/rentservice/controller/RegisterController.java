package com.besliCar.rentservice.controller;

import com.besliCar.rentservice.client.CustomerServiceClient;
import com.besliCar.rentservice.dto.AuthenticationRequest;
import com.besliCar.rentservice.dto.AuthenticationResponse;
import com.besliCar.rentservice.dto.CreateCustomerRequest;
import com.besliCar.rentservice.dto.CustomerDto;
import com.besliCar.rentservice.service.RegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/register")
public class RegisterController {
    private final RegisterService registerService;
    private final CustomerServiceClient customerServiceClient;


    public RegisterController(RegisterService registerService, CustomerServiceClient customerServiceClient) {
        this.registerService = registerService;
        this.customerServiceClient = customerServiceClient;
    }
    @GetMapping("/customer/{name}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable String name) {
        return ResponseEntity.ok(customerServiceClient.getCustomerByName(name)).getBody();
    }

    @PostMapping
    public ResponseEntity<AuthenticationResponse> registerCustomer(@RequestBody CreateCustomerRequest request) {
        return ResponseEntity.ok(registerService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(registerService.authenticate(request));
    }
}
