package com.squad7.desafiolocadorasolutis.controller;

import com.squad7.desafiolocadorasolutis.controller.request.DriverCodeEmailValidationRequest;
import com.squad7.desafiolocadorasolutis.controller.request.DriverPostRequest;
import com.squad7.desafiolocadorasolutis.controller.request.DriverSendCodeEmailValidationRequest;
import com.squad7.desafiolocadorasolutis.controller.response.DriverResponse;
import com.squad7.desafiolocadorasolutis.controller.response.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Driver")
public interface DriverController {

    @Operation(summary = "Register a driver", description = "Register a new driver based on the information provided in the DriverPostRequest object.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Driver registered successfully"),
            @ApiResponse(responseCode = "409", description = "Driver already registered")
    })
    ResponseEntity<ResponseMessage> registerDriver(DriverPostRequest driverRequest);

    @Operation(summary = "Send an email code", description = "Send an email code to email for validation.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Code sent"),
            @ApiResponse(responseCode = "404", description = "Driver not found"),
            @ApiResponse(responseCode = "409", description = "Driver email already confirmed")
    })
    ResponseEntity<ResponseMessage> sendCodeToEmailValidation(DriverSendCodeEmailValidationRequest driverCodeValidation);

    @Operation(summary = "Validate an email code", description = "Validates the code sent by email.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Code validated"),
            @ApiResponse(responseCode = "404", description = "Driver not found"),
            @ApiResponse(responseCode = "409", description = "Driver email already confirmed"),
            @ApiResponse(responseCode = "400", description = "The code does not match the code sent")
    })
    ResponseEntity<ResponseMessage> validateCodeEmail(DriverCodeEmailValidationRequest driverCodeValidation);

    ResponseEntity<List<DriverResponse>> getAllDrivers();
}
