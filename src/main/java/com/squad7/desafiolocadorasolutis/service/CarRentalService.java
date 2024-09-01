package com.squad7.desafiolocadorasolutis.service;

import com.squad7.desafiolocadorasolutis.controller.request.CarRentalPostRequest;
import com.squad7.desafiolocadorasolutis.controller.response.CarRentalResponse;
import com.squad7.desafiolocadorasolutis.enums.CarRentalStatusEnum;

import java.util.List;
import java.util.UUID;

public interface CarRentalService {

     void rentCar(CarRentalPostRequest carRental);
     List<CarRentalResponse> getAllCarsFiltered(String employeeRegister, String cpf, List<CarRentalStatusEnum> statusList);

    void confirmRent(UUID uuid);

    void startRent(UUID uuid);

    void finishRent(UUID uuid);

    void cancelRent(UUID uuid);
}
