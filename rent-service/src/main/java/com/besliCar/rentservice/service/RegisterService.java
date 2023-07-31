package com.besliCar.rentservice.service;

import com.besliCar.rentservice.client.CustomerServiceClient;
import com.besliCar.rentservice.dto.AuthenticationRequest;
import com.besliCar.rentservice.dto.AuthenticationResponse;
import com.besliCar.rentservice.dto.CreateCustomerRequest;
import com.besliCar.rentservice.dto.CustomerDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private final CustomerServiceClient customerServiceClient;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public RegisterService(CustomerServiceClient customerServiceClient, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {

        this.customerServiceClient = customerServiceClient;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(CreateCustomerRequest request) {
        request.setPassWord(passwordEncoder.encode(request.getPassWord()));
        CustomerDto customerDto = customerServiceClient.createCustomer(request).getBody();
        var jwtToken = jwtService.generateToken(customerDto);
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getName(),
                        request.getPassword()
                )
        );
        CustomerDto customer = customerServiceClient.getCustomerByName(request.getName()).getBody();
        customerServiceClient.updateCustomerRoleToUser(customer.getId());
        var jwtToken = jwtService.generateToken(customer);
        return new AuthenticationResponse(jwtToken);


    }

}

