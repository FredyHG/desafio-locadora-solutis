package com.squad7.desafiolocadorasolutis.service.impl;

import com.squad7.desafiolocadorasolutis.controller.request.CarRentalPostRequest;
import com.squad7.desafiolocadorasolutis.mappers.CarRentalMapper;
import com.squad7.desafiolocadorasolutis.model.Car;
import com.squad7.desafiolocadorasolutis.model.CarRental;
import com.squad7.desafiolocadorasolutis.model.Driver;
import com.squad7.desafiolocadorasolutis.repository.CarRentalRepository;
import com.squad7.desafiolocadorasolutis.service.CarRentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarRentalServiceImpl implements CarRentalService {

    private final CarRentalRepository carRentalRepository;
    private final DriverServiceImpl driverService;
    private final CarServiceImpl carService;

    public void rentCar(CarRentalPostRequest carRental) {

        Driver driver = driverService.ensureDriverExistsByCpf(carRental.getDriverCpf());

        Car car = carService.ensureCarExistsById(carRental.getCarId());

        CarRental savedCarRental = CarRentalMapper.INSTANCE.requestToModel(carRental);
        savedCarRental.setDriver(driver);
        savedCarRental.setCar(car);
        savedCarRental.getRentalTerms().setAcceptBy(driver);
        carRentalRepository.save(savedCarRental);
    }

}


