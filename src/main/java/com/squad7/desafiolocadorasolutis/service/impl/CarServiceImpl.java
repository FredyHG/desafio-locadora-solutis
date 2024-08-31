package com.squad7.desafiolocadorasolutis.service.impl;

import com.squad7.desafiolocadorasolutis.controller.request.CarPostRequest;
import com.squad7.desafiolocadorasolutis.controller.response.CarResponse;
import com.squad7.desafiolocadorasolutis.enums.Category;
import com.squad7.desafiolocadorasolutis.exception.CarAlreadyRegisteredException;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
        log.info(":: registerCar() - Response {} ::", carToBeSaved);
    }

    @Override
    public List<Car> getAllCarsFiltered(Category category, List<String> idsAccessories) {
        return carRepository.getAllCarsFiltered(category, idsAccessories);
    }

    private void ensureCarNotRegisteredByChassis(String chassis) {
        carRepository.findByChassis(chassis).ifPresent(car -> {
            throw new CarAlreadyRegisteredException("Car already registered with chassis: " + chassis);
        });
    }

    @Override
    public CarResponse getCarByUuid(UUID carId) {
        log.info(":: getCarByUuid() - Request {} ::", carId);

        Car car = carRepository.findById(carId).orElseThrow(
                () -> new CarNotFoundException("No cars found by id: " + carId));

        log.info(":: getCarByUuid() - Response {} ::", car);
        return CarMapper.INSTANCE.modelToResponse(car);
    }
}
