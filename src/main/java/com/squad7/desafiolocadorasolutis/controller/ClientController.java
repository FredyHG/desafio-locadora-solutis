package com.squad7.desafiolocadorasolutis.controller;

import com.squad7.desafiolocadorasolutis.controller.request.ClientCodeEmailValidationRequest;
import com.squad7.desafiolocadorasolutis.controller.request.ClientPostRequest;
import com.squad7.desafiolocadorasolutis.controller.request.ClientSendCodeEmailValidationRequest;
import com.squad7.desafiolocadorasolutis.controller.response.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface ClientController {

    @Operation(summary = "Register a client", description = "Register a new client based on the information provided in the ClientPostRequest object.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client registered successfully"),
            @ApiResponse(responseCode = "409", description = "Client already registered")
    })
    ResponseEntity<ResponseMessage> registerClient(ClientPostRequest clientRequest);

    @Operation(summary = "Send an email code", description = "Send an email code to email for validation.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Code sent"),
            @ApiResponse(responseCode = "404", description = "Client not found"),
            @ApiResponse(responseCode = "409", description = "Client email already confirmed")
    })
    ResponseEntity<?> sendCodeToEmailValidation(ClientSendCodeEmailValidationRequest clientCodeValidation);

    @Operation(summary = "Validate an email code", description = "Validates the code sent by email.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Code validated"),
            @ApiResponse(responseCode = "404", description = "Client not found"),
            @ApiResponse(responseCode = "409", description = "Client email already confirmed"),
            @ApiResponse(responseCode = "400", description = "The code does not match the code sent")
    })
    ResponseEntity<?> validateCodeEmail(ClientCodeEmailValidationRequest clientCodeValidation);
}
