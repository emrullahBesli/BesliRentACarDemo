package com.besliCar.carservice.dto

import com.besliCar.carservice.model.CarStatus
import com.besliCar.carservice.model.Gear

data class CarDto(
        val id:String,
        val carBrand: String,
        val carModel: String,
        val carGear: Gear,
        val carStatus: CarStatus
)
