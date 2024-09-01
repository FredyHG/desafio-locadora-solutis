package com.squad7.desafiolocadorasolutis.controller.impl;

import com.squad7.desafiolocadorasolutis.controller.EmployeeController;
import com.squad7.desafiolocadorasolutis.controller.response.EmployeeResponse;
import com.squad7.desafiolocadorasolutis.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/employee")
public class EmployeeControllerImpl implements EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    @Override
    public ResponseEntity<List<EmployeeResponse>> getAll() {
        List<EmployeeResponse> response = employeeService.getAll();
        return ResponseEntity.ok(response);
    }
}
