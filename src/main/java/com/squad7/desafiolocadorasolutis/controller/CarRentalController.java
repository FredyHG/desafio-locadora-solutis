package com.squad7.desafiolocadorasolutis.controller;

import com.squad7.desafiolocadorasolutis.controller.request.CarRentalPostRequest;
import com.squad7.desafiolocadorasolutis.controller.response.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;


public interface CarRentalController {

    @Operation(summary = "Register a car rental", description = "Register a new car rental based on the information provided in the RentalRequest object.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Rental created successfully"),
            @ApiResponse(responseCode = "409", description = "Car is not available for the selected period")
    })
    ResponseEntity<ResponseMessage> rentCar(CarRentalPostRequest rentalRequest);

}
