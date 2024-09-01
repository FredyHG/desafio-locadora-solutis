package com.squad7.desafiolocadorasolutis.controller.impl;

import com.squad7.desafiolocadorasolutis.controller.CarRentalController;
import com.squad7.desafiolocadorasolutis.controller.request.CarRentalPostRequest;
import com.squad7.desafiolocadorasolutis.controller.response.CarRentalResponse;
import com.squad7.desafiolocadorasolutis.controller.response.ResponseMessage;
import com.squad7.desafiolocadorasolutis.enums.CarRentalStatusEnum;
import com.squad7.desafiolocadorasolutis.service.CarRentalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("api/v1/car/rent")
@RequiredArgsConstructor
public class CarRentalControllerImpl implements CarRentalController {

    private final CarRentalService carRentalService;

    @PostMapping
    @Override
    public ResponseEntity<ResponseMessage> rentCar(@RequestBody @Valid CarRentalPostRequest carRentalRequest) {
        log.info("Receive request to rent car with ID: {} for driver with CPF: {}", carRentalRequest.getCarId(), carRentalRequest.getDriverCpf());

        carRentalService.rentCar(carRentalRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseMessage
                .builder()
                .code(HttpStatus.CREATED.value())
                .message("Rental created successfully")
                .build());
    }

    @GetMapping("/filter")
    @Override
    public ResponseEntity<List<CarRentalResponse>> getAllCarsFiltered(@RequestParam(name = "employeeRegister", required = false) String employeeRegister,
                                                                      @RequestParam(name = "driverCpf", required = false) String driverCpf,
                                                                      @RequestParam(name = "status", required = false) List<CarRentalStatusEnum> statusList) {
        List<CarRentalResponse> response = carRentalService.getAllCarsFiltered(employeeRegister, driverCpf, statusList);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{rentId}/confirm")
    @Override
    public ResponseEntity<ResponseMessage> confirmRent(@PathVariable(name = "rentId") String rentId) {

        carRentalService.confirmRent(UUID.fromString(rentId));

        return ResponseEntity.status(HttpStatus.OK).body(ResponseMessage
                        .builder()
                        .code(HttpStatus.OK.value())
                        .message("Confirm Rental successfully")
                        .build());
    }

    @PostMapping("/{rentId}/start")
    @Override
    public ResponseEntity<ResponseMessage> startRent(@PathVariable(name = "rentId") String rentId) {

        carRentalService.startRent(UUID.fromString(rentId));

        return ResponseEntity.status(HttpStatus.OK).body(ResponseMessage
                .builder()
                .message("Start rent successfully")
                .code(HttpStatus.OK.value())
                .build());
    }

    @PostMapping("/{rentId}/finish")
    @Override
    public ResponseEntity<ResponseMessage> finishRent(@PathVariable(name = "rentId") String rentId) {
        carRentalService.finishRent(UUID.fromString(rentId));

        return ResponseEntity.status(HttpStatus.OK).body(ResponseMessage
                .builder()
                .message("Rent finished successfully")
                .code(HttpStatus.OK.value())
                .build());
    }

    @PostMapping("/{rentId}/cancel")
    @Override
    public ResponseEntity<ResponseMessage> cancelRent(@PathVariable(name = "rentId") String rentId) {
        carRentalService.cancelRent(UUID.fromString(rentId));

        return ResponseEntity.status(HttpStatus.OK).body(ResponseMessage
                .builder()
                .message("Rent canceled successfully")
                .code(HttpStatus.OK.value())
                .build());
    }
}


