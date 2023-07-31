package com.besliCar.rentservice.controller;

import com.besliCar.rentservice.dto.*;
import com.besliCar.rentservice.model.Rent;
import com.besliCar.rentservice.service.RentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/rent")
public class RentController {
    private final RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @GetMapping
    public List<CarDto> getAllCarsRentable() {
        return rentService.getAllCarsRentable();
    }

    @PostMapping()
    public ResponseEntity<String> rent(@RequestBody RentRequest request) {
        return ResponseEntity.ok(rentService.startRentProcess(request));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Rent>stopRentProcess(@PathVariable String id){
        return ResponseEntity.ok(rentService.stopRentProcess(id));
    }

}
