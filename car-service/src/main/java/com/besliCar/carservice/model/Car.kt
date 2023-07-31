package com.besliCar.carservice.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.hibernate.annotations.GenericGenerator

@Entity
data class Car @JvmOverloads constructor(
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val id: String? = "",
        val carBrand: String,
        val carModel: String,
        val carGear: Gear,
        var carStatus: CarStatus

)

enum class CarStatus {
    RENTABLE,
    UNRENTABLE
}

enum class Gear {
    AUTOMATİC,
    MANUEL
}