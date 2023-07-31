package com.besliCar.carservice;

import com.besliCar.carservice.model.Car;
import com.besliCar.carservice.model.CarStatus;
import com.besliCar.carservice.model.Gear;
import com.besliCar.carservice.repository.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class CarServiceApplication implements CommandLineRunner {

	public CarServiceApplication(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(CarServiceApplication.class, args);
	}
	private  final CarRepository carRepository;

	@Override
	public void run(String... args) throws Exception {
		Car car = carRepository.save(new Car("Fiat","Egea", Gear.AUTOMATİC, CarStatus.RENTABLE));
		Car car1 = carRepository.save(new Car("Renault","Clio", Gear.MANUEL, CarStatus.UNRENTABLE));
		Car car2 = carRepository.save(new Car("Toyota","Corola", Gear.AUTOMATİC, CarStatus.UNRENTABLE));

	}
}
