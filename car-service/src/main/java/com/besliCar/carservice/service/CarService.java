package com.besliCar.carservice.service;

import com.besliCar.carservice.dto.CarDto;
import com.besliCar.carservice.dto.CarDtoConverter;
import com.besliCar.carservice.exception.CarCouldNotFoundByIdException;
import com.besliCar.carservice.exception.RentableCarsNotFoundException;
import com.besliCar.carservice.model.Car;
import com.besliCar.carservice.model.CarStatus;
import com.besliCar.carservice.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final CarRepository carRepository;
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    public CarDto getCarById(String id){
        Car car = carRepository.findById(id).orElseThrow(()->new CarCouldNotFoundByIdException("Car could not found"));
        return CarDtoConverter.convertToCarDto(car);
    }
    public List<CarDto> getAllRentableCars(){
        List<Car> cars=carRepository.findByCarStatus(CarStatus.RENTABLE).orElseThrow(()->new RentableCarsNotFoundException("Could Not Found Rentable Cars"));
        return cars.stream().map(CarDtoConverter::convertToCarDto).collect(Collectors.toList());
    }

    public void updateCarStatusToRentable(String id){
            Car car = carRepository.findById(id).orElseThrow(()->new CarCouldNotFoundByIdException("Car could not found"));
            car.setCarStatus(CarStatus.RENTABLE);
            carRepository.save(car);
    }

    public void updateCarStatusToUnrentable(String id) {
        Car car = carRepository.findById(id).orElseThrow(()->new CarCouldNotFoundByIdException("Car could not found"));
        car.setCarStatus(CarStatus.UNRENTABLE);
        carRepository.save(car);
    }
}
