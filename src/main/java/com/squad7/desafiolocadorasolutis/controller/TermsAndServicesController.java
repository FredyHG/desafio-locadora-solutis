package com.squad7.desafiolocadorasolutis.controller;

import com.squad7.desafiolocadorasolutis.service.TermsAndServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/termsAndServices")
public class TermsAndServicesController {

    @Autowired
    private TermsAndServices termsAndServices;

    @PostMapping("/accept/{cpf}")
    public ResponseEntity<String> accept(@PathVariable ("cpf") String cpf){
        String responseMessage = "Não existem termos pendentes de aceitação, para esse cpf.";
        if (termsAndServices.accept(cpf)){
            responseMessage = "Termos aceitos com sucesso.";
        }
        return new ResponseEntity<String>(responseMessage, HttpStatus.OK);
    }
}
