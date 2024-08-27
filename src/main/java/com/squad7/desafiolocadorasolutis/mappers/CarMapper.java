package com.squad7.desafiolocadorasolutis.mappers;

import com.squad7.desafiolocadorasolutis.controller.request.CarPostRequest;
import com.squad7.desafiolocadorasolutis.model.Accessory;
import com.squad7.desafiolocadorasolutis.model.Car;
import com.squad7.desafiolocadorasolutis.model.CarModel;

import java.util.List;

public class CarMapper {

    public static Car requestToModel(CarPostRequest car, List<Accessory> accessory, CarModel carModel) {
        return new Car(car.getPricePerDay(), car.getChassis(), car.getLicensePlate(), carModel, accessory);
    }

}
