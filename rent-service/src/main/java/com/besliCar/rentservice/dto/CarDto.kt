package com.besliCar.rentservice.dto

data class CarDto(
        val id:String,
        val carBrand: String,
        val carModel: String,
        val carGear: Gear,
        val carStatus: CarStatus
)
enum class CarStatus {
    RENTABLE,
    UNRENTABLE
}

enum class Gear {
    AUTOMATİC,
    MANUEL
}
