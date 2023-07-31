package com.besliCar.customerservice.dto

import com.besliCar.customerservice.model.CustomerStatus
import com.besliCar.customerservice.model.Roles

data class CustomerDto(
        val id: String,
        var name: String,
        var passWord: String,
        var roles: Roles,
        var customerStatus: CustomerStatus
)




