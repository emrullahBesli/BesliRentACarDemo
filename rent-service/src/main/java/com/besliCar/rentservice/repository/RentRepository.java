package com.besliCar.rentservice.repository;

import com.besliCar.rentservice.model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<Rent,String> {
}
