package com.besliCar.customerservice.dto;

import com.besliCar.customerservice.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerConvert {

    public static CustomerDto convert(Customer customer){
        return new CustomerDto(customer.getId(),customer.getName(),customer.getPassword(),customer.getRoles(),customer.getCustomerStatus());
    }
}
