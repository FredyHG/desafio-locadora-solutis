package com.squad7.desafiolocadorasolutis.controller;

import com.squad7.desafiolocadorasolutis.controller.request.CarPostRequest;
import com.squad7.desafiolocadorasolutis.controller.response.CarResponse;
import com.squad7.desafiolocadorasolutis.controller.response.ResponseMessage;
import com.squad7.desafiolocadorasolutis.enums.CarRentalStatusEnum;
import com.squad7.desafiolocadorasolutis.enums.CategoryEnum;
import com.squad7.desafiolocadorasolutis.model.Car;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

@Tag(name = "Car")
public interface CarController {

    @Operation(summary = "Register a car", description = "Register a new car based on the information provided in the CarPostRequest object.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Car registered successfully"),
            @ApiResponse(responseCode = "409", description = "Car already registered")
    })
    ResponseEntity<ResponseMessage> registerCar(CarPostRequest car);

    @Operation(summary = "Find a car by UUID", description = "Find a car based on the carId path param.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car registered successfully"),
            @ApiResponse(responseCode = "404", description = "Car not found")
    })
    ResponseEntity<CarResponse> getCarByUuid(UUID carId);
  

    @Operation(summary = "Get all cars by filter", description = "Retrieve all cars with optional filters by category and accessory IDs  and rental status.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cars retrieved successfully")
    })
    ResponseEntity<List<Car>> getAllCars(CategoryEnum categoryEnum, List<String> accessoryIds, CarRentalStatusEnum carRentalStatus);

    @Operation(summary = "Get all cars", description = "Retrieve all cars.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cars retrieved successfully")
    })
    ResponseEntity<List<Car>> getAllCars();
}
