package com.squad7.desafiolocadorasolutis.service;

import com.squad7.desafiolocadorasolutis.controller.request.CarRentalPostRequest;
import com.squad7.desafiolocadorasolutis.controller.response.CarRentalResponse;
import com.squad7.desafiolocadorasolutis.enums.CarRentalStatusEnum;

import java.util.List;

public interface CarRentalService {

     void rentCar(CarRentalPostRequest carRental);
     List<CarRentalResponse> getAllCarsFiltered(String cpf, List<CarRentalStatusEnum> statusList);
}
