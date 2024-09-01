package com.squad7.desafiolocadorasolutis.controller.impl;

import com.squad7.desafiolocadorasolutis.controller.CarController;
import com.squad7.desafiolocadorasolutis.controller.request.CarPostRequest;
import com.squad7.desafiolocadorasolutis.controller.response.CarResponse;
import com.squad7.desafiolocadorasolutis.controller.response.ResponseMessage;
import com.squad7.desafiolocadorasolutis.enums.CategoryEnum;
import com.squad7.desafiolocadorasolutis.model.Car;
import com.squad7.desafiolocadorasolutis.repository.CarRentalRepository;
import com.squad7.desafiolocadorasolutis.service.CarService;
import com.squad7.desafiolocadorasolutis.service.facade.PaymentFacade;
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
@RequestMapping("api/v1/car")
@RequiredArgsConstructor
public class CarControllerImpl implements CarController {

    private final CarService carService;

    @PostMapping("/create")
    @Override
    public ResponseEntity<ResponseMessage> registerCar(@RequestBody @Valid CarPostRequest car) {
        log.info("Receive request to register new car with chassis: {}", car.getChassis());
        carService.registerCar(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseMessage
                .builder()
                .code(HttpStatus.CREATED.value())
                .message("Car registered successfully")
                .build());
    }

    @GetMapping("/filter")
    @Override
    public ResponseEntity<List<Car>> getAllCars(@RequestParam(required = false) CategoryEnum categoryEnum,
            @RequestParam(required = false) List<String> accessoryIds) {
        return ResponseEntity.status(HttpStatus.OK).body(carService.getAllCarsFiltered(categoryEnum, accessoryIds));
    }

    @GetMapping(value = "/{carId}/detail")
    @Override
    public ResponseEntity<CarResponse> getCarByUuid(@PathVariable(name = "carId", required = true) UUID carId) {
        CarResponse response = carService.getCarByUuid(carId);
        return ResponseEntity.ok().body(response);
    }
}
