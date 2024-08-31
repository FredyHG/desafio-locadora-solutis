package com.squad7.desafiolocadorasolutis.service.impl;

import com.squad7.desafiolocadorasolutis.model.Driver;
import com.squad7.desafiolocadorasolutis.repository.DriverRepository;
import com.squad7.desafiolocadorasolutis.service.DriverService;
import com.squad7.desafiolocadorasolutis.service.TermsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TermsServiceImpl implements TermsService {

    private final DriverService driveService;
    private final DriverRepository driverRepository;

    @Override
    public void acceptTerms(String cpf) {

        Driver driverToBeAccepted = driveService.ensureDriverExistsByCpf(cpf);
        Driver driver = driverToBeAccepted.acceptTerms();

        driverRepository.save(driver);
    }
}
