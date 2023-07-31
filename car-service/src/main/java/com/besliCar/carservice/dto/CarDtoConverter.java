package com.besliCar.carservice.dto;


import com.besliCar.carservice.model.Car;
import org.springframework.stereotype.Component;

@Component
public class CarDtoConverter {

    public static CarDto convertToCarDto(Car car){
        return new CarDto(car.getId(),car.getCarBrand(), car.getCarBrand(),car.getCarGear(),car.getCarStatus());
    }

}
