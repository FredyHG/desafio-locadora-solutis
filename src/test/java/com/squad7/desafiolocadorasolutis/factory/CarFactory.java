package com.squad7.desafiolocadorasolutis.factory;

import com.squad7.desafiolocadorasolutis.controller.request.CarPostRequest;
import com.squad7.desafiolocadorasolutis.controller.response.CarResponse;
import com.squad7.desafiolocadorasolutis.dto.AccessoryRequestDTO;
import com.squad7.desafiolocadorasolutis.model.Accessory;
import com.squad7.desafiolocadorasolutis.model.Car;
import org.instancio.Instancio;
import org.instancio.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.instancio.Select.field;

public class CarFactory {

    public static final Model<Car> CAR =
            Instancio.of(Car.class)
                    .set(field(Car::getAccessories), AccessoryFactory.withSize(3))
                    .toModel();

    public static final Model<CarPostRequest> CAR_POST_REQUEST =
            Instancio.of(CarPostRequest.class)
                    .set(field(CarPostRequest::getCarModelId), UUID.randomUUID().toString())
                    .set(field(CarPostRequest::getAccessoriesIds), List.of(new AccessoryRequestDTO(UUID.randomUUID().toString())))
                    .toModel();

    public static final Model<CarResponse> CAR_RESPONSE =
            Instancio.of(CarResponse.class)
                    .toModel();

    public static Car validCar() {
        return Instancio.of(CAR)
                .create();
    }

    public static CarResponse validCarResponse() {
        return Instancio.of(CAR_RESPONSE)
                .create();
    }

    public static CarResponse validCarResponseByCar(Car car) {
        return Instancio.of(CAR_RESPONSE)
                .set(field(CarResponse::getChassis), car.getChassis())
                .create();
    }

    public static List<Car> withSize(int size) {
        return Instancio.ofList(CAR)
                .size(size)
                .create();
    }

    public static CarPostRequest validCarPostRequest() {
        return Instancio.of(CAR_POST_REQUEST)
                .create();
    }
}
