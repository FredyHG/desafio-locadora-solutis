package com.squad7.desafiolocadorasolutis.controller.impl;


import com.squad7.desafiolocadorasolutis.controller.DriverController;
import com.squad7.desafiolocadorasolutis.controller.request.DriverCodeEmailValidationRequest;
import com.squad7.desafiolocadorasolutis.controller.request.DriverPostRequest;
import com.squad7.desafiolocadorasolutis.controller.request.DriverSendCodeEmailValidationRequest;
import com.squad7.desafiolocadorasolutis.controller.response.ResponseMessage;
import com.squad7.desafiolocadorasolutis.service.DriverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/driver")
@RequiredArgsConstructor
public class DriverControllerImpl implements DriverController {
    private final DriverService driverService;

    @PostMapping
    @Override
    public ResponseEntity<ResponseMessage> registerDriver(@RequestBody @Valid DriverPostRequest driverRequest) {

        log.info("Receive request to register new driver with CPF: {}", driverRequest.getCpf());
        driverService.registerDriver(driverRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseMessage.builder()
                .code(HttpStatus.CREATED.value())
                .message("Driver registered successfully")
                .build());
    }

    @PostMapping("/code/email")
    @Override
    public ResponseEntity<ResponseMessage> sendCodeToEmailValidation(@RequestBody @Valid DriverSendCodeEmailValidationRequest driverCodeValidation) {
        log.info(":: sendCodeToEmailValidation() - Request: {}", driverCodeValidation);
        driverService.sendCodeToEmailValidation(driverCodeValidation);
        return ResponseEntity.ok(ResponseMessage.builder()
                .code(HttpStatus.OK.value())
                .message("Email code validation send")
                .build());
    }

    @PostMapping("/code/email/validate")
    @Override
    public ResponseEntity<ResponseMessage> validateCodeEmail(@RequestBody @Valid DriverCodeEmailValidationRequest driverCodeValidation) {
        log.info(":: validateCodeEmail() - Request: {}", driverCodeValidation);
        driverService.validateCodeEmail(driverCodeValidation);
        return ResponseEntity.ok(ResponseMessage.builder()
                .code(HttpStatus.OK.value())
                .message("Email code validated")
                .build());
    }
}
