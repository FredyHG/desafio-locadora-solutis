package com.squad7.desafiolocadorasolutis.service;

import com.squad7.desafiolocadorasolutis.controller.request.CarPostRequest;
import com.squad7.desafiolocadorasolutis.controller.response.CarResponse;
import com.squad7.desafiolocadorasolutis.enums.CategoryEnum;
import com.squad7.desafiolocadorasolutis.model.Car;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface CarService {

    void registerCar(CarPostRequest car);
    List<Car> getAllCarsFiltered(CategoryEnum categoryEnum, List<String > idsAccessories);
    CarResponse getCarByUuid(UUID carId);
    void ensureCarNotRegisteredByChassis(String chassis);
    void ensureCarAvailableByPeriod(UUID id, LocalDate startRental, LocalDate endRental);
    Car ensureCarExistsById(UUID carId);
}
