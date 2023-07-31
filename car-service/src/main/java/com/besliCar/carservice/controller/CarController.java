package com.besliCar.carservice.controller;

import com.besliCar.carservice.dto.CarDto;
import com.besliCar.carservice.model.Gear;
import com.besliCar.carservice.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/car")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<CarDto>> getAllCarsRentable() {
        return ResponseEntity.ok(carService.getAllRentableCars());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable String id){
        return ResponseEntity.ok(carService.getCarById(id));
    }
    @PutMapping("/rentable/{id}")
    public void updateCarStatusToRentable(@PathVariable String id){
        carService.updateCarStatusToRentable(id);
    }
    @PutMapping("/unrentable/{id}")
    public void updateCarStatusToUnrentable(@PathVariable String id){
        carService.updateCarStatusToUnrentable(id);
    }

}
