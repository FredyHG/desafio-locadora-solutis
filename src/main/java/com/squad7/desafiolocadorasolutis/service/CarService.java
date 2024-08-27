package com.squad7.desafiolocadorasolutis.service;

import com.squad7.desafiolocadorasolutis.controller.request.CarPostRequest;
import com.squad7.desafiolocadorasolutis.controller.response.CarResponse;
import com.squad7.desafiolocadorasolutis.enums.Category;
import com.squad7.desafiolocadorasolutis.model.Car;

import java.util.List;
import java.util.UUID;

public interface CarService {

    void registerCar(CarPostRequest car);

    List<Car> getAllCarsFiltered(Category category, List<String > idsAccessories);

    CarResponse getCarByUuid(UUID carId);
}
