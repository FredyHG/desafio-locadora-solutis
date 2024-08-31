package com.squad7.desafiolocadorasolutis.service.impl;

import com.squad7.desafiolocadorasolutis.model.Driver;
import com.squad7.desafiolocadorasolutis.repository.DriverRepository;
import com.squad7.desafiolocadorasolutis.service.DriverService;
import com.squad7.desafiolocadorasolutis.service.TermsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class TermsServiceImpl implements TermsService {

    private final DriverService driveService;
    private final DriverRepository driverRepository;

    @Override
    public void acceptTerms(String cpf) {
        log.info(":: acceptTerms() - Attempting to accept terms for driver with CPF: {} ::", cpf);
        Driver driverToBeAccepted = driveService.ensureDriverExistsByCpf(cpf);
        Driver driver = driverToBeAccepted.acceptTerms();

        driverRepository.save(driver);

        log.info(":: acceptTerms() - Terms accepted successfully for driver with CPF: {} ::", cpf);
    }
}
