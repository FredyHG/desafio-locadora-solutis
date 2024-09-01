package com.squad7.desafiolocadorasolutis.controller;

import com.squad7.desafiolocadorasolutis.controller.response.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface TermsController {

    @Operation(summary = "Accept terms of service", description = "Accepts the terms of service for a user identified by CPF.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Terms accepted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid CPF provided")
    })
    ResponseEntity<ResponseMessage> acceptTerms(String cpf);
}
