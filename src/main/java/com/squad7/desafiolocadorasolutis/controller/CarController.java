package com.squad7.desafiolocadorasolutis.controller;

import com.squad7.desafiolocadorasolutis.controller.request.CarPostRequest;
import com.squad7.desafiolocadorasolutis.controller.response.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface CarController {

    @Operation(summary = "Register a car", description = "Register a new car based on the information provided in the CarPostRequest object.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Car registered successfully"),
            @ApiResponse(responseCode = "409", description = "Car already registered")
    })
    ResponseEntity<ResponseMessage> registerCar(CarPostRequest car);
}
