package com.squad7.desafiolocadorasolutis.service.impl;

import com.squad7.desafiolocadorasolutis.controller.request.DriverCodeEmailValidationRequest;
import com.squad7.desafiolocadorasolutis.controller.request.DriverPostRequest;
import com.squad7.desafiolocadorasolutis.controller.request.DriverSendCodeEmailValidationRequest;
import com.squad7.desafiolocadorasolutis.enums.AccountEmailStatusEnum;
import com.squad7.desafiolocadorasolutis.exception.DriverAlreadyExistsException;
import com.squad7.desafiolocadorasolutis.exception.DriverEmailCodeNotValid;
import com.squad7.desafiolocadorasolutis.exception.DriverNotFoundException;
import com.squad7.desafiolocadorasolutis.mappers.DriverMapper;
import com.squad7.desafiolocadorasolutis.model.Driver;
import com.squad7.desafiolocadorasolutis.repository.DriverRepository;
import com.squad7.desafiolocadorasolutis.service.DriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService{

    private static final String EMAIL_VALIDATION_CODE = "3321";
    private final DriverRepository driverRepository;

    public void registerDriver(DriverPostRequest driverPostRequest) {
        log.info(":: registerDriver() - Attempting to register driver with CPF: {} ::", driverPostRequest);
        ensureDriverExistsByCpf(driverPostRequest.getCpf());
        ensureDriverNotRegisteredByEmail(driverPostRequest.getEmail());

        Driver driverToBeSaved = DriverMapper.INSTANCE.requestToModel(driverPostRequest);
        driverToBeSaved.setAccountEmailStatusEnum(AccountEmailStatusEnum.TO_CONFIRM);

        driverToBeSaved = driverRepository.save(driverToBeSaved);
        log.info(":: registerDriver() - Driver registered successfully: {} ::", driverToBeSaved);
    }

    @Override
    public void sendCodeToEmailValidation(DriverSendCodeEmailValidationRequest request) {
        log.info(":: sendCodeToEmailValidation() - Sending validation code to email: {} ::", request.getEmail());
        Driver driver = findByEmail(request.getEmail());
        ensureDriverEmailIsNotAlreadyConfirmed(driver);
        log.info(":: sendCodeToEmailValidation() - Validation code sent successfully to email: {} ::", request.getEmail());
    }

    @Override
    public void validateCodeEmail(DriverCodeEmailValidationRequest request) {
        log.info(":: validateCodeEmail() - Validating code for email: {} ::", request.getEmail());
        Driver driver = findByEmail(request.getEmail());
        ensureDriverEmailIsNotAlreadyConfirmed(driver);

        if (!request.getCode().equals(EMAIL_VALIDATION_CODE)){
            log.warn(":: validateCodeEmail() - Invalid code for email: {} ::", request.getEmail());
            throw new DriverEmailCodeNotValid("Invalid email code.");
        }

        driver.setAccountEmailStatusEnum(AccountEmailStatusEnum.CONFIRMED);
        driverRepository.save(driver);
        log.info(":: validateCodeEmail() - Code validated successfully for email: {} ::", request.getEmail());
    }

    @Override
    public Driver ensureDriverExistsByCpf(String cpf){
        log.info(":: ensureDriverExistsByCpf() - Checking if driver exists for the cpf: {} ::", cpf);
        return findByCpf(cpf)
                .orElseThrow(() -> new DriverNotFoundException("No Driver found by cpf: "+ cpf));
    }

    @Override
    public Optional<Driver> findByCpf(String cpf){
        return driverRepository.findByCpf(cpf);
    }

    private Driver findByEmail(String email){
        log.info(":: findByEmail() - Checking if driver exists for the email: {} ::", email);
        return driverRepository.findByEmail(email)
                .orElseThrow(() -> new DriverNotFoundException("No Driver found by email: "+email));
    }

    private void ensureDriverNotRegisteredByEmail(String email) {
        log.info(":: ensureDriverNotRegisteredByEmail() - Ensuring no driver is registered with email: {} ::", email);
        if (driverRepository.existsByEmail(email)) {
            log.warn(":: ensureDriverNotRegisteredByEmail() - The driver already exists with an email informed: {} ::", email);
            throw new DriverAlreadyExistsException("Driver with email " + email + " already exists.");
        }
    }

    private void ensureDriverEmailIsNotAlreadyConfirmed(Driver driver){
        log.info(":: ensureDriverEmailIsNotAlreadyConfirmed() - Checking if email {} is already confirmed: ::", driver.getEmail());
        if (driver.getAccountEmailStatusEnum().equals(AccountEmailStatusEnum.CONFIRMED)){
            throw new DriverAlreadyExistsException("Driver email already confirmed: "+ driver.getEmail());
        }
    }
}