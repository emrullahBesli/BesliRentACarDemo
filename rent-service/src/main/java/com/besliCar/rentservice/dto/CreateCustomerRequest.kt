package com.besliCar.rentservice.dto

import jakarta.validation.constraints.NotNull

data class CreateCustomerRequest(
        @field:NotNull(message = "Name must be not Null")
        val name:String,
        @field:NotNull(message = "Password must be not Null")
        var passWord:String
)
