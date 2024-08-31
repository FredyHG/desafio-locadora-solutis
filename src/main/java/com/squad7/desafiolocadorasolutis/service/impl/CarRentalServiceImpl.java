package com.squad7.desafiolocadorasolutis.service.impl;

import com.squad7.desafiolocadorasolutis.controller.request.CarRentalPostRequest;
import com.squad7.desafiolocadorasolutis.enums.CarRentalStatus;
import com.squad7.desafiolocadorasolutis.mappers.CarRentalMapper;
import com.squad7.desafiolocadorasolutis.model.Car;
import com.squad7.desafiolocadorasolutis.model.CarRental;
import com.squad7.desafiolocadorasolutis.model.Driver;
import com.squad7.desafiolocadorasolutis.repository.CarRentalRepository;
import com.squad7.desafiolocadorasolutis.service.CarRentalService;
import com.squad7.desafiolocadorasolutis.service.facade.PaymentFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarRentalServiceImpl implements CarRentalService {

    private final CarRentalRepository carRentalRepository;
    private final DriverServiceImpl driverService;
    private final CarServiceImpl carService;
    private final PaymentFacade paymentFacade;

    public void rentCar(CarRentalPostRequest carRental) {
        log.info("Starting car rental process for driver with CPF: {}", carRental.getDriverCpf());

        Driver driver = driverService.ensureDriverExistsByCpf(carRental.getDriverCpf());
        log.info("Driver found: CPF = {}, Name = {}", driver.getCpf(), driver.getName());

        Car car = carService.ensureCarExistsById(carRental.getCarId());
        log.info("Car found: ID = {}, Chassis = {}", car.getId(), car.getChassis());

        CarRental savedCarRental = CarRentalMapper.INSTANCE.requestToModel(carRental);
        savedCarRental.setDriver(driver);
        savedCarRental.getPayment().setDriver(driver);
        savedCarRental.setCar(car);
        savedCarRental.getRentalTerms().setAcceptBy(driver);
        savedCarRental.calculateTotalPrice();

        BigDecimal amount = paymentFacade.makePayment(
                savedCarRental.getPayment().getPaymentMethod(),
                savedCarRental.getPrice());

        savedCarRental.setPrice(amount);

        carRentalRepository.save(savedCarRental);

        log.info("Car rental process finished for driver with CPF: {}", carRental.getDriverCpf());
    }
}


