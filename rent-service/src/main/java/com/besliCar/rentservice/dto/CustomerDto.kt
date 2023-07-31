package com.besliCar.rentservice.dto

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class CustomerDto(
        val id:String,
        var name:String,
        var passWord:String,
        var roles:Roles,
        var customerStatus:CustomerStatus
):UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return roles.name.let { role ->
            mutableListOf(SimpleGrantedAuthority(role))
        }
    }

    override fun getPassword(): String {
       return passWord
    }

    override fun getUsername(): String {
        return name
    }

    override fun isAccountNonExpired(): Boolean {
       return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    enum class Roles {
        Guest,
        User
    }
    enum class CustomerStatus {
        Idle,
        Rented

    }
}
