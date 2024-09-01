package com.squad7.desafiolocadorasolutis.service.impl;

import com.squad7.desafiolocadorasolutis.controller.request.CarPostRequest;
import com.squad7.desafiolocadorasolutis.controller.response.CarResponse;
import com.squad7.desafiolocadorasolutis.enums.CategoryEnum;
import com.squad7.desafiolocadorasolutis.exception.CarAlreadyRegisteredException;
import com.squad7.desafiolocadorasolutis.exception.CarNotAvailableException;
import com.squad7.desafiolocadorasolutis.exception.CarNotFoundException;
import com.squad7.desafiolocadorasolutis.mappers.CarMapper;
import com.squad7.desafiolocadorasolutis.model.Accessory;
import com.squad7.desafiolocadorasolutis.model.Car;
import com.squad7.desafiolocadorasolutis.model.CarModel;
import com.squad7.desafiolocadorasolutis.repository.CarRepository;
import com.squad7.desafiolocadorasolutis.service.AccessoryService;
import com.squad7.desafiolocadorasolutis.service.CarModelService;
import com.squad7.desafiolocadorasolutis.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarModelService carModelService;
    private final AccessoryService accessoryService;

    @Override
    public void registerCar(CarPostRequest car) {
        log.info(":: registerCar() - Request {} ::", car);
        ensureCarNotRegisteredByChassis(car.getChassis());

        Car carToBeSaved = CarMapper.INSTANCE.requestToModel(car);
        List<Accessory> accessories = accessoryService.findById(carToBeSaved.getAccessories());
        CarModel carModel = carModelService.findById(UUID.fromString(car.getCarModelId()));

        carToBeSaved.setAccessories(accessories);
        carToBeSaved.setCarModel(carModel);

        carRepository.save(carToBeSaved);
        log.info(":: registerCar() - Car successfully registered with id: {}  ::", carToBeSaved);
    }

    @Override
    public List<Car> getAllCarsFiltered(CategoryEnum categoryEnum, List<String> idsAccessories) {
        return carRepository.getAllCarsFiltered(categoryEnum, idsAccessories);
    }

    public Car ensureCarExistsById(UUID carId) {
        return carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException("No car found by id: " + carId));
    }

    public void ensureCarAvailableByPeriod(UUID id, LocalDate startRental, LocalDate endRental){
        carRepository.findByCarUuidAndRentalPeriod(id, startRental, endRental).ifPresent(car -> {
            throw new CarNotAvailableException("Car not available from " + startRental + " to " + endRental);
        });
    }

    private void ensureCarNotRegisteredByChassis(String chassis) {
        carRepository.findByChassis(chassis).ifPresent(car -> {
            throw new CarAlreadyRegisteredException("Car already registered with chassis: " + chassis);
        });
    }

    @Override
    public CarResponse getCarByUuid(UUID carId) {
        log.info(":: getCarByUuid() - Request received for car id: {} ::", carId);

        Car car = carRepository.findById(carId).orElseThrow(
                () -> new CarNotFoundException("No cars found by id: " + carId));

        log.info(":: getCarByUuid() - Car found: {} ::", car);
        return CarMapper.INSTANCE.modelToResponse(car);
    }
}
