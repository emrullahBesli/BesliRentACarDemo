package com.besliCar.customerservice.service;

import com.besliCar.customerservice.dto.CreateCustomerRequest;
import com.besliCar.customerservice.dto.CustomerConvert;
import com.besliCar.customerservice.dto.CustomerDto;
import com.besliCar.customerservice.exception.CustomerCouldNotFoundByIdException;
import com.besliCar.customerservice.exception.CustomerCouldNotFoundByNameException;
import com.besliCar.customerservice.model.Customer;
import com.besliCar.customerservice.model.CustomerStatus;
import com.besliCar.customerservice.model.Roles;
import com.besliCar.customerservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDto getCustomerById(String id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerCouldNotFoundByIdException("Customer Could Not Found By Id"));
        return CustomerConvert.convert(customer);
    }

    public CustomerDto getCustomerByName(String name) {
        Customer customer = customerRepository.findByName(name).orElseThrow(() -> new CustomerCouldNotFoundByNameException("Customer Could Not Found By Name"));
        return new CustomerDto(customer.getId(), customer.getName(), customer.getPassword(), customer.getRoles(), customer.getCustomerStatus());
    }

    public CustomerDto createCustomer(CreateCustomerRequest request) {
        Customer customer = new Customer(request.getName(), request.getPassWord(), Roles.Guest, CustomerStatus.Idle);
        return CustomerConvert.convert(customerRepository.save(customer));
    }

    public void updateCustomerRoleToUser(String id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerCouldNotFoundByIdException("Customer Could Not Found"));
        customer.setRoles(Roles.User);
        customerRepository.save(customer);
    }

    public void updateCustomerStatusToRented(String id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerCouldNotFoundByIdException("Customer Could Not Found"));
        customer.setCustomerStatus(CustomerStatus.Rented);
        customerRepository.save(customer);
    }
    public void updateCustomerStatusToIdle(String id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerCouldNotFoundByIdException("Customer Could Not Found"));
        customer.setCustomerStatus(CustomerStatus.Idle);
        customerRepository.save(customer);
    }

}
