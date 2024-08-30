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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService{

    private static final String EMAIL_VALIDATION_CODE = "3321";
    private final DriverRepository driverRepository;

    public void registerDriver(DriverPostRequest driverPostRequest) {
        ensureDriverExistsByCpf(driverPostRequest.getCpf());
        ensureDriverNotRegisteredByEmail(driverPostRequest.getEmail());

        Driver driverToBeSaved = DriverMapper.INSTANCE.requestToModel(driverPostRequest);
        driverToBeSaved.setAccountEmailStatusEnum(AccountEmailStatusEnum.TO_CONFIRM);

        driverToBeSaved = driverRepository.save(driverToBeSaved);
        log.info(":: registerDriver() - Driver Saved: {} ::", driverToBeSaved);
    }

    @Override
    public void sendCodeToEmailValidation(DriverSendCodeEmailValidationRequest request) {
        Driver driver = findByEmail(request.getEmail());
        ensureDriverEmailIsNotAlreadyConfirmed(driver);
    }

    @Override
    public void validateCodeEmail(DriverCodeEmailValidationRequest request) {
        Driver driver = findByEmail(request.getEmail());
        ensureDriverEmailIsNotAlreadyConfirmed(driver);

        if (!request.getCode().equals(EMAIL_VALIDATION_CODE)){
            throw new DriverEmailCodeNotValid("Invalid email code.");
        }

        driver.setAccountEmailStatusEnum(AccountEmailStatusEnum.CONFIRMED);
        driverRepository.save(driver);
    }

    @Override
    public Driver ensureDriverExistsByCpf(String cpf){
        return findByCpf(cpf)
                .orElseThrow(() -> new DriverNotFoundException("No Driver found by cpf: "+ cpf));
    }

    @Override
    public Optional<Driver> findByCpf(String cpf){
        return driverRepository.findByCpf(cpf);
    }

    private Driver findByEmail(String email){
        return driverRepository.findByEmail(email)
                .orElseThrow(() -> new DriverNotFoundException("No Driver found by email: "+email));
    }

    private void ensureDriverNotRegisteredByEmail(String email) {
        if (driverRepository.existsByEmail(email)) {
            throw new DriverAlreadyExistsException("Driver with email " + email + " already exists.");
        }
    }

    private void ensureDriverEmailIsNotAlreadyConfirmed(Driver driver){
        if (driver.getAccountEmailStatusEnum().equals(AccountEmailStatusEnum.CONFIRMED)){
            throw new DriverAlreadyExistsException("Driver email already confirmed: "+ driver.getEmail());
        }
    }
}