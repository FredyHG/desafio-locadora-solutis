package com.squad7.desafiolocadorasolutis.service.impl;

import com.squad7.desafiolocadorasolutis.controller.request.DriverCodeEmailValidationRequest;
import com.squad7.desafiolocadorasolutis.controller.request.DriverPostRequest;
import com.squad7.desafiolocadorasolutis.controller.request.DriverSendCodeEmailValidationRequest;
import com.squad7.desafiolocadorasolutis.enums.AccountEmailStatusEnum;
import com.squad7.desafiolocadorasolutis.exception.*;
import com.squad7.desafiolocadorasolutis.mappers.DriverMapper;
import com.squad7.desafiolocadorasolutis.model.Driver;
import com.squad7.desafiolocadorasolutis.repository.DriverRepository;
import com.squad7.desafiolocadorasolutis.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService{


    Logger logger = LoggerFactory.getLogger(DriverServiceImpl.class);
    private static final String EMAIL_VALIDATION_CODE = "3321";
    private final DriverRepository driverRepository;

    public void registerDriver(DriverPostRequest driverPostRequest) {

        ensureDriverNotRegisteredByCpf(driverPostRequest.getCpf());
        ensureDriverNotRegisteredByEmail(driverPostRequest.getEmail());

        Driver driverToBeSaved = DriverMapper.INSTANCE.requestToModel(driverPostRequest);
        driverToBeSaved.setAccountEmailStatusEnum(AccountEmailStatusEnum.TO_CONFIRM);

        driverToBeSaved = driverRepository.save(driverToBeSaved);
        logger.info(":: registerDriver() - Driver Saved: {} ::", driverToBeSaved);
    }

    @Override
    public void sendCodeToEmailValidation(DriverSendCodeEmailValidationRequest request) {
        Driver driver = getByEmail(request.getEmail());
        ensureDriverEmailIsNotAlreadyConfirmed(driver);
    }

    @Override
    public void validateCodeEmail(DriverCodeEmailValidationRequest request) {
        Driver driver = getByEmail(request.getEmail());
        ensureDriverEmailIsNotAlreadyConfirmed(driver);

        if (!request.getCode().equals(EMAIL_VALIDATION_CODE)){
            throw new DriverEmailCodeNotValid("Invalid email code.");
        }

        driver.setAccountEmailStatusEnum(AccountEmailStatusEnum.CONFIRMED);
        driverRepository.save(driver);
    }

    private Driver getByEmail(String email){
        return driverRepository.findByEmail(email)
                .orElseThrow(() -> new DriverNotFoundException("No Driver found by email: "+email));
    }

    private void ensureDriverNotRegisteredByEmail(String email) {
        if (driverRepository.existsByEmail(email)) {
            throw new DriverAlreadyExistsException("Driver with email " + email + " already exists.");
        }
    }

    private void ensureClientNotRegisteredByCpf(String cpf) {
        if (driverRepository.existsByCpf(cpf)) {
            throw new DriverAlreadyExistsException("Driver with CPF " + cpf + " already exists.");
        }
    }

    @Override
    public boolean existsByCpf(String cpf){
        return driverRepository.existsByCpf(cpf);
    }

    @Override
    public void acceptTermsAndServices(String cpf) {
        Optional<Driver> driver = driverRepository.getByCpf(cpf);
        if (driver.isPresent()){
            Driver driverToSave = driver.get();
            driverToSave.setBlocked(false);
            driverRepository.save(driverToSave);
        }
    }

    @Override
    public Driver getByCpf(String cpf) {
        return driverRepository.getByCpf(cpf).get();
    }

    @Override
    public Driver getByCpfAndBlockedFalse(String cpf) {
        return driverRepository.getByCpfAndBlockedFalse(cpf).orElse(null);
    }


    private void ensureDriverEmailIsNotAlreadyConfirmed(Driver driver){
        if (driver.getAccountEmailStatusEnum().equals(AccountEmailStatusEnum.CONFIRMED)){
            throw new DriverAlreadyExistsException("Driver email already confirmed: "+driver.getEmail());
        }
    }
    private void ensureDriverNotRegisteredByCpf(String cpf) {
        if (driverRepository.existsByCpf(cpf)) {
            throw new DriverAlreadyExistsException("Client with CPF " + cpf + " already exists.");
        }
    }
}