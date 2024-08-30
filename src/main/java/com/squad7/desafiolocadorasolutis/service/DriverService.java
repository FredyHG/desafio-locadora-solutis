package com.squad7.desafiolocadorasolutis.service;

import com.squad7.desafiolocadorasolutis.controller.request.DriverCodeEmailValidationRequest;
import com.squad7.desafiolocadorasolutis.controller.request.DriverPostRequest;
import com.squad7.desafiolocadorasolutis.controller.request.DriverSendCodeEmailValidationRequest;
import com.squad7.desafiolocadorasolutis.model.Driver;

import java.util.Optional;

public interface DriverService{
    void registerDriver(DriverPostRequest driverPostRequest);
    void sendCodeToEmailValidation(DriverSendCodeEmailValidationRequest request);
    void validateCodeEmail(DriverCodeEmailValidationRequest request);
    Driver ensureDriverExistsByCpf(String cpf);
    Optional<Driver> findByCpf(String cpf);
}

