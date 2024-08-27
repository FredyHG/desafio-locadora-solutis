package com.squad7.desafiolocadorasolutis.service;

import com.squad7.desafiolocadorasolutis.controller.request.CarPostRequest;
import com.squad7.desafiolocadorasolutis.enums.Category;
import com.squad7.desafiolocadorasolutis.model.Car;

import java.util.List;

public interface CarService {

    void registerCar(CarPostRequest car);

    List<Car> getAllCarsFiltered(Category category, List<String > idsAccessories);
}
