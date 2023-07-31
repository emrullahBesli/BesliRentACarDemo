package com.besliCar.carservice.repository;

import com.besliCar.carservice.model.Car;
import com.besliCar.carservice.model.CarStatus;
import com.besliCar.carservice.model.Gear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {
    Optional<List<Car>> findByCarStatus(CarStatus carStatus);

    Optional<List<Car>> findByCarGear(Gear carGear);
}
