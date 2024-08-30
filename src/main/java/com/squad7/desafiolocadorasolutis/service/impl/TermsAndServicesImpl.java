package com.squad7.desafiolocadorasolutis.service.impl;

import com.squad7.desafiolocadorasolutis.service.DriverService;
import com.squad7.desafiolocadorasolutis.service.TermsAndServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TermsAndServicesImpl implements TermsAndServices {

    @Autowired
    private DriverService driveService;

    @Override
    public boolean accept(String cpf) {
        if(driveService.existsByCpf(cpf)){
            driveService.acceptTermsAndServices(cpf);
            return true;
        } else {
            return false;
        }
    }
}
