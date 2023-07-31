package com.besliCar.customerservice.dto

import jakarta.validation.constraints.NotNull

data class CreateCustomerRequest(
        @field:NotNull(message = "Name must be not Null")
        val name:String,
        @field:NotNull(message = "Password must be not Null")
        val passWord:String
)
