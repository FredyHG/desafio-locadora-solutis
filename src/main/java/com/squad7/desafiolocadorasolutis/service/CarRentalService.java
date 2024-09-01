package com.squad7.desafiolocadorasolutis.service;

import com.squad7.desafiolocadorasolutis.controller.request.CarRentalPostRequest;
import com.squad7.desafiolocadorasolutis.controller.response.CarRentalResponse;
import com.squad7.desafiolocadorasolutis.enums.CarRentalStatusEnum;
import com.squad7.desafiolocadorasolutis.model.CarRental;

import java.util.List;
import java.util.UUID;

public interface CarRentalService {

     void rentCar(CarRentalPostRequest carRental);
     void confirmRent(UUID carRentalId);
     void startRent(UUID rentId);
     void finishRent(UUID rentId);
     void cancelRent(UUID rentId);
     CarRental ensureCarRentalExistsById(UUID carRentalId);
     List<CarRentalResponse> getAllCarsFiltered(String cpf, List<CarRentalStatusEnum> statusList);
}
