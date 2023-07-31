package com.besliCar.rentservice.service;

import com.besliCar.rentservice.client.CarServiceClient;
import com.besliCar.rentservice.client.CustomerServiceClient;
import com.besliCar.rentservice.dto.CarDto;
import com.besliCar.rentservice.dto.CarStatus;
import com.besliCar.rentservice.dto.CustomerDto;
import com.besliCar.rentservice.dto.RentRequest;
import com.besliCar.rentservice.exception.CarCannotBeRentedException;
import com.besliCar.rentservice.exception.RentCouldNotFoundByIdException;
import com.besliCar.rentservice.model.Rent;
import com.besliCar.rentservice.repository.RentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RentService {
    private final RentRepository rentRepository;
    private final CarServiceClient carServiceClient;
    private final CustomerServiceClient customerServiceClient;

    private final RabbitTemplate rabbitTemplate;
    private final DirectExchange exchange;
    private final Logger logger = LoggerFactory.getLogger(RentService.class);

    public RentService(RentRepository rentRepository, CarServiceClient carServiceClient, CustomerServiceClient customerServiceClient, RabbitTemplate rabbitTemplate, DirectExchange exchange) {
        this.rentRepository = rentRepository;
        this.carServiceClient = carServiceClient;
        this.customerServiceClient = customerServiceClient;
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
    }
    @Value("${sample.rabbitmq.routingKey}")
    private String routingKey;

    public List<CarDto> getAllCarsRentable() {
        return carServiceClient.getAllCarsRentable().getBody();
    }

    public String startRentProcess(RentRequest request) {
        rabbitTemplate.convertAndSend(exchange.getName(), routingKey, request);
        return "Rent Process is start";
    }

    @RabbitListener(queues = "${sample.rabbitmq.queue}")
    public void rentingControl(RentRequest request) {
        CarDto carDto = carServiceClient.getCarById(request.getCarId()).getBody();
        CustomerDto customerDto = customerServiceClient.getCustomerById(request.getCustomerId()).getBody();
        if (carDto.getCarStatus() == CarStatus.RENTABLE && customerDto.getCustomerStatus()== CustomerDto.CustomerStatus.Idle) {
            logger.info("Renting is start");
            rabbitTemplate.convertAndSend(exchange.getName(), "secondRoute", request);
        }else rabbitTemplate.convertAndSend(exchange.getName(), "errorRoute", new CarCannotBeRentedException("You Cannot Rent Any Car"));

    }

    @RabbitListener(queues = "secondStepQueue")
    public void completingRent(RentRequest request) {
        carServiceClient.updateCarStatusToUnrentable(request.getCarId());
        customerServiceClient.updateCustomerToRented(request.getCustomerId());
        Rent rent = new Rent(request.getCustomerId(), request.getCarId(), LocalDateTime.now(), LocalDateTime.now(), BigDecimal.ZERO);
        rent = rentRepository.save(rent);
        System.out.println(rent.getId());
    }

    public Rent stopRentProcess(String id) {
        Rent rent = rentRepository.findById(id).orElseThrow(() -> new RentCouldNotFoundByIdException("You Have Not Rent Any Car"));

        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = rent.getStartTime();

        long seconds = Duration.between(startTime, endTime).getSeconds();
        long minutes = seconds / 60;

        BigDecimal pricePerMinute = BigDecimal.valueOf(2);
        BigDecimal totalPrice = pricePerMinute.multiply(BigDecimal.valueOf(minutes));

        rent.setEndTime(endTime);
        rent.setCost(totalPrice);
        rentRepository.save(rent);
        carServiceClient.updateCarStatusToRentable(rent.getCarId());
        customerServiceClient.updateCustomerToIdle(rent.getCustomerId());
        return rent;
    }

    @RabbitListener(queues = "errorQueue")
    public void takeError(Exception e){
        logger.info(e.getMessage());
    }

}
