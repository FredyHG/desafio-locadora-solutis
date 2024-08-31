package com.squad7.desafiolocadorasolutis.controller.impl;


import com.squad7.desafiolocadorasolutis.controller.CarRentalController;
import com.squad7.desafiolocadorasolutis.controller.request.CarRentalPostRequest;
import com.squad7.desafiolocadorasolutis.controller.response.CarRentalResponse;
import com.squad7.desafiolocadorasolutis.controller.response.ResponseMessage;
import com.squad7.desafiolocadorasolutis.enums.CarRentalStatus;
import com.squad7.desafiolocadorasolutis.service.impl.CarRentalServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/rentals")
@RequiredArgsConstructor
public class CarRentalControllerImpl implements CarRentalController {

    private final CarRentalServiceImpl carRentalService;

    @PostMapping
    public ResponseEntity<ResponseMessage> rentCar(@RequestBody @Valid CarRentalPostRequest carRentalRequest) {
        log.info("Receive request to rent car with ID: {} for driver with CPF: {}", carRentalRequest.getCarId(), carRentalRequest.getDriverCpf());

        carRentalService.rentCar(carRentalRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseMessage
                .builder()
                .code(HttpStatus.CREATED.value())
                .message("Rental created successfully")
                .build());
    }

    @GetMapping
    @Override
    public ResponseEntity<List<CarRentalResponse>> getAllCarsFiltered(@RequestHeader(name = "cpf") String cpf,
                                                                      @RequestParam(name = "status") List<CarRentalStatus> statusList) {
        List<CarRentalResponse> response = carRentalService.getAllCarsFiltered(cpf, statusList);
        return ResponseEntity.ok(response);
    }
}


