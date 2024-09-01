package com.squad7.desafiolocadorasolutis.service.impl;

import com.squad7.desafiolocadorasolutis.controller.request.DriverCodeEmailValidationRequest;
import com.squad7.desafiolocadorasolutis.controller.request.DriverPostRequest;
import com.squad7.desafiolocadorasolutis.controller.request.DriverSendCodeEmailValidationRequest;
import com.squad7.desafiolocadorasolutis.enums.AccountEmailStatusEnum;
import com.squad7.desafiolocadorasolutis.exception.DriverAlreadyExistsException;
import com.squad7.desafiolocadorasolutis.exception.DriverNotFoundException;
import com.squad7.desafiolocadorasolutis.exception.EmailNotConfirmedException;
import com.squad7.desafiolocadorasolutis.exception.InvalidEmailCodeException;
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
public class DriverServiceImpl implements DriverService {

    private static final String EMAIL_VALIDATION_CODE = "3321";
    private final DriverRepository driverRepository;

    @Override
    public void registerDriver(DriverPostRequest driverPostRequest) {
        log.info(":: registerDriver() - Attempting to register driver with CPF: {} ::", driverPostRequest);
        Driver driverToBeSaved = DriverMapper.INSTANCE.requestToModel(driverPostRequest);

        driverToBeSaved.checkIfMinor();
        ensureDriverNotExistsByCpf(driverToBeSaved.getCpf());
        ensureDriverNotRegisteredByEmail(driverToBeSaved.getEmail());

        driverToBeSaved.setAccountEmailStatusEnum(AccountEmailStatusEnum.TO_CONFIRM);
        Driver saved = driverRepository.save(driverToBeSaved);
        log.info(":: registerDriver() - Driver registered successfully: {} ::", saved);
    }

    @Override
    public void sendCodeToEmailValidation(DriverSendCodeEmailValidationRequest request) {
        log.info(":: sendCodeToEmailValidation() - Sending validation code to email: {} ::", request.getEmail());
        Driver driver = ensureDriverExistsByEmail(request.getEmail());
        ensureDriverEmailIsNotAlreadyConfirmed(driver);
        log.info(":: sendCodeToEmailValidation() - Validation code sent successfully to email: {} ::", request.getEmail());
    }

    @Override
    public void validateCodeEmail(DriverCodeEmailValidationRequest request) {
        log.info(":: validateCodeEmail() - Validating code for email: {} ::", request.getEmail());
        Driver driver = ensureDriverExistsByEmail(request.getEmail());
        ensureDriverEmailIsNotAlreadyConfirmed(driver);

        if (!request.getCode().equals(EMAIL_VALIDATION_CODE)){
            log.warn(":: validateCodeEmail() - Invalid code for email: {} ::", request.getEmail());
            throw new InvalidEmailCodeException("Invalid email code.");
        }

        driver.setAccountEmailStatusEnum(AccountEmailStatusEnum.CONFIRMED);
        driverRepository.save(driver);
        log.info(":: validateCodeEmail() - Code validated successfully for email: {} ::", request.getEmail());
    }

    @Override
    public void ensureDriverConfirmEmailTermsByEmail(String email) {
        log.info("Checking if driver has confirmed email");
        Driver driver = ensureDriverExistsByEmail(email);

        if(driver.getAccountEmailStatusEnum() != AccountEmailStatusEnum.CONFIRMED) {
            throw new EmailNotConfirmedException("Driver not has confirmed email");
        }
    }

    @Override
    public void ensureDriverAcceptAccountTermsByEmail(String email) {
        log.info("Checking if driver has accepted account terms");
        Driver driver = ensureDriverExistsByEmail(email);

        if(driver.getAccountTerms().getAcceptAt() == null) {
            throw new RuntimeException("Driver not accept terms");
        }
    }

    @Override
    public Driver ensureDriverExistsByCpf(String cpf){
        log.info(":: ensureDriverExistsByCpf() - Checking if driver exists for the cpf: {} ::", cpf);
        return findByCpf(cpf)
                .orElseThrow(() -> new DriverNotFoundException("No Driver found by cpf: "+ cpf));
    }

    @Override
    public Driver ensureDriverExistsByEmail(String email){
        log.info(":: findByEmail() - Checking if driver exists for the email: {} ::", email);
        return findByEmail(email)
                .orElseThrow(() -> new DriverNotFoundException("No Driver found by email: "+email));
    }

    private Optional<Driver> findByCpf(String cpf){
        return driverRepository.findByCpf(cpf);
    }

    private Optional<Driver> findByEmail(String email){
        return driverRepository.findByEmail(email);
    }

    private void ensureDriverNotRegisteredByEmail(String email) {
        log.info(":: ensureDriverNotRegisteredByEmail() - Ensuring no driver is registered with email: {} ::", email);
        if (driverRepository.existsByEmail(email)) {
            log.warn(":: ensureDriverNotRegisteredByEmail() - The driver already exists with an email informed: {} ::", email);
            throw new DriverAlreadyExistsException("Driver with email " + email + " already exists.");
        }
    }

    @Override
    public void ensureDriverEmailIsNotAlreadyConfirmed(Driver driver){
        log.info(":: ensureDriverEmailIsNotAlreadyConfirmed() - Checking if email {} is already confirmed: ::", driver.getEmail());
        if (driver.getAccountEmailStatusEnum().equals(AccountEmailStatusEnum.CONFIRMED)){
            throw new DriverAlreadyExistsException("Driver email already confirmed: "+ driver.getEmail());
        }
    }

    @Override
    public void ensureDriverNotExistsByCpf(String cpf){
        log.info(":: ensureDriverNotExistsByCpf() - Checking if driver exists for the cpf: {} ::", cpf);
        if (driverRepository.existsByCpf(cpf)){
            log.warn(":: ensureDriverNotExistsByCpf() - The driver already exists with an cpf informed: {} ::", cpf);
            throw new DriverAlreadyExistsException("Driver with cpf " + cpf + " already exists.");
        }
    }
}