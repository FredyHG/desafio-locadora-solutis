package com.squad7.desafiolocadorasolutis.controller;

import com.squad7.desafiolocadorasolutis.controller.response.EmployeeResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeController {

    ResponseEntity<List<EmployeeResponse>> getAll();
}
