package com.squad7.desafiolocadorasolutis.service;

import com.squad7.desafiolocadorasolutis.controller.request.DriverCodeEmailValidationRequest;
import com.squad7.desafiolocadorasolutis.controller.request.DriverPostRequest;
import com.squad7.desafiolocadorasolutis.controller.request.DriverSendCodeEmailValidationRequest;
import com.squad7.desafiolocadorasolutis.model.Driver;

public interface DriverService{
    void registerDriver(DriverPostRequest driverPostRequest);

    boolean existsByCpf(String cpf);

    void acceptTermsAndServices(String cpf);

    Driver getByCpf(String cpf);

    Driver getByCpfAndBlockedFalse(String cpf);


    void sendCodeToEmailValidation(DriverSendCodeEmailValidationRequest request);
    void validateCodeEmail(DriverCodeEmailValidationRequest request);
}

