package com.squad7.desafiolocadorasolutis.service;

import com.squad7.desafiolocadorasolutis.controller.request.DriverCodeEmailValidationRequest;
import com.squad7.desafiolocadorasolutis.controller.request.DriverPostRequest;
import com.squad7.desafiolocadorasolutis.controller.request.DriverSendCodeEmailValidationRequest;
import com.squad7.desafiolocadorasolutis.model.Driver;

public interface DriverService{
    void registerDriver(DriverPostRequest driverPostRequest);
    void sendCodeToEmailValidation(DriverSendCodeEmailValidationRequest request);
    void validateCodeEmail(DriverCodeEmailValidationRequest request);
    void ensureDriverConfirmEmailTermsByEmail(String email);
    void ensureDriverAcceptAccountTermsByEmail(String email);
    void ensureDriverNotExistsByCpf(String cpf);
    void ensureDriverEmailIsNotAlreadyConfirmed(Driver driver);
    Driver ensureDriverExistsByEmail(String email);
    Driver ensureDriverExistsByCpf(String cpf);
}

