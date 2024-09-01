package com.squad7.desafiolocadorasolutis.controller.impl;


import com.squad7.desafiolocadorasolutis.controller.CarRentalController;
import com.squad7.desafiolocadorasolutis.controller.request.CarRentalPostRequest;
import com.squad7.desafiolocadorasolutis.controller.response.CarRentalResponse;
import com.squad7.desafiolocadorasolutis.controller.response.ResponseMessage;
import com.squad7.desafiolocadorasolutis.enums.CarRentalStatusEnum;
import com.squad7.desafiolocadorasolutis.service.impl.CarRentalServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/car/rent")
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
    public ResponseEntity<List<CarRentalResponse>> getAllCarsFiltered(@RequestParam(name = "cpf") String cpf,
                                                                      @RequestParam(name = "status") List<CarRentalStatusEnum> statusList) {
        List<CarRentalResponse> response = carRentalService.getAllCarsFiltered(cpf, statusList);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/confirm")
    public ResponseEntity<ResponseMessage> confirmRent(@RequestParam(name = "rentId") String rentId) {

        carRentalService.confirmRent(UUID.fromString(rentId));

        return ResponseEntity.status(HttpStatus.OK).body(ResponseMessage
                        .builder()
                        .code(HttpStatus.OK.value())
                        .message("Confirm Rental successfully")
                        .build());
    }

    @PostMapping("/start")
    public ResponseEntity<ResponseMessage> startRent(@RequestParam(name = "rentId") String rentId) {

        carRentalService.startRent(UUID.fromString(rentId));

        return ResponseEntity.status(HttpStatus.OK).body(ResponseMessage
                .builder()
                .message("Start rent successfully")
                .code(HttpStatus.OK.value())
                .build());
    }

    @PostMapping("/finish")
    public ResponseEntity<ResponseMessage> finishRent(@RequestParam(name = "rentId") String rentId) {
        carRentalService.finishRent(UUID.fromString(rentId));

        return ResponseEntity.status(HttpStatus.OK).body(ResponseMessage
                .builder()
                .message("Rent finished successfully")
                .code(HttpStatus.OK.value())
                .build());
    }

    @PostMapping("/cancel")
    public ResponseEntity<ResponseMessage> cancelRent(@RequestParam(name = "rentId") String rentId) {
        carRentalService.cancelRent(UUID.fromString(rentId));

        return ResponseEntity.status(HttpStatus.OK).body(ResponseMessage
                .builder()
                .message("Rent canceled successfully")
                .code(HttpStatus.OK.value())
                .build());
    }
}


