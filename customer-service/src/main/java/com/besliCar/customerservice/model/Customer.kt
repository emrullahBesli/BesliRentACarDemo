package com.besliCar.customerservice.model


import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.hibernate.annotations.GenericGenerator


@Entity
data class Customer @JvmOverloads constructor(
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val id: String = "",
        @Column(name = "name", unique = true)
        var name:String,
        var password:String,
        var roles:Roles,
        var customerStatus:CustomerStatus
)

