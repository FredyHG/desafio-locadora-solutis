package com.squad7.desafiolocadorasolutis.controller;

import com.squad7.desafiolocadorasolutis.controller.request.CarRentalPostRequest;
import com.squad7.desafiolocadorasolutis.controller.response.CarRentalResponse;
import com.squad7.desafiolocadorasolutis.controller.response.ResponseMessage;
import com.squad7.desafiolocadorasolutis.enums.CarRentalStatusEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface CarRentalController {

    @Operation(summary = "Register a car rental", description = "Register a new car rental based on the information provided in the RentalRequest object.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Rental created successfully"),
            @ApiResponse(responseCode = "409", description = "Car is not available for the selected period")
    })
    ResponseEntity<ResponseMessage> rentCar(CarRentalPostRequest rentalRequest);

    ResponseEntity<List<CarRentalResponse>> getAllCarsFiltered(String employeeRegister, String cpf, List<CarRentalStatusEnum> statusList);
    ResponseEntity<ResponseMessage> confirmRent(String rentId);
    ResponseEntity<ResponseMessage> finishRent(String rentId);
    ResponseEntity<ResponseMessage> startRent(String rentId);
    ResponseEntity<ResponseMessage> cancelRent(String rentId);
}
