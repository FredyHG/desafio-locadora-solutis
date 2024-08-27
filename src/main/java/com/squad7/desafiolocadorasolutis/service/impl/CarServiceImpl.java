package com.squad7.desafiolocadorasolutis.service.impl;

import com.squad7.desafiolocadorasolutis.controller.request.CarPostRequest;
import com.squad7.desafiolocadorasolutis.enums.Category;
import com.squad7.desafiolocadorasolutis.exception.CarAlreadyRegisteredException;
import com.squad7.desafiolocadorasolutis.mappers.CarMapper;
import com.squad7.desafiolocadorasolutis.model.Accessory;
import com.squad7.desafiolocadorasolutis.model.Car;
import com.squad7.desafiolocadorasolutis.model.CarModel;
import com.squad7.desafiolocadorasolutis.repository.CarRepository;
import com.squad7.desafiolocadorasolutis.service.AccessoryService;
import com.squad7.desafiolocadorasolutis.service.CarModelService;
import com.squad7.desafiolocadorasolutis.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarModelService carModelService;
    private final AccessoryService accessoryService;

    @Override
    public void registerCar(CarPostRequest car) {
        ensureCarNotRegisteredByChassis(car.getChassis());

        Accessory accessory = accessoryService.findById(UUID.fromString(car.getAccessoriesIds().get(0).getId()));
        CarModel carModel = carModelService.findById(UUID.fromString(car.getCarModelId()));

        Car carToBeSaved = CarMapper.requestToModel(car, List.of(accessory), carModel);

        carRepository.save(carToBeSaved);
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
}
