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

    @Operation(summary = "Get all car rentals filtered", description = "Retrieve a list of all car rentals filtered by CPF and rental status.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of rentals retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No rentals found with the provided filters")
    })
    ResponseEntity<List<CarRentalResponse>> getAllCarsFiltered(String cpf, List<CarRentalStatusEnum> statusList);

    @Operation(summary = "Confirm a car rental", description = "Confirm a car rental based on the provided rent ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental confirmed successfully"),
            @ApiResponse(responseCode = "404", description = "Rental not found"),
            @ApiResponse(responseCode = "400", description = "Rental cannot be confirmed")
    })
    ResponseEntity<ResponseMessage> confirmRent(String rentId);

    @Operation(summary = "Finish a car rental", description = "Finish a car rental based on the provided rent ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental finished successfully"),
            @ApiResponse(responseCode = "404", description = "Rental not found"),
            @ApiResponse(responseCode = "400", description = "Rental cannot be finished")
    })
    ResponseEntity<ResponseMessage> finishRent(String rentId);

    @Operation(summary = "Start a car rental", description = "Start a car rental based on the provided rent ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental started successfully"),
            @ApiResponse(responseCode = "404", description = "Rental not found"),
            @ApiResponse(responseCode = "400", description = "Rental cannot be started")
    })
    ResponseEntity<ResponseMessage> startRent(String rentId);

    @Operation(summary = "Cancel a car rental", description = "Cancel a car rental based on the provided rent ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental canceled successfully"),
            @ApiResponse(responseCode = "404", description = "Rental not found"),
            @ApiResponse(responseCode = "400", description = "Rental cannot be canceled")
    })
    ResponseEntity<ResponseMessage> cancelRent(String rentId);
}
