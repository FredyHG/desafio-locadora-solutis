package com.squad7.desafiolocadorasolutis.controller;

import com.squad7.desafiolocadorasolutis.controller.response.EmployeeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeController {

    @Operation(summary = "Get all employees", description = "Retrieve all employees.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employees retrieved successfully")
    })
    ResponseEntity<List<EmployeeResponse>> getAll();
}
