package com.squad7.desafiolocadorasolutis.service.impl;

import com.squad7.desafiolocadorasolutis.service.ClientService;
import com.squad7.desafiolocadorasolutis.service.TermsAndServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TermsAndServicesImpl implements TermsAndServices {

    @Autowired
    private ClientService clientService;

    @Override
    public boolean accept(String cpf) {
        if(clientService.existsByCpf(cpf)){
            clientService.acceptTermsAndServices(cpf);
            return true;
        } else {
            return false;
        }
    }
}
