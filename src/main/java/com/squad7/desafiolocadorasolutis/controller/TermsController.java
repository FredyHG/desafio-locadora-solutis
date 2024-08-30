package com.squad7.desafiolocadorasolutis.controller;

import com.squad7.desafiolocadorasolutis.controller.response.ResponseMessage;
import org.springframework.http.ResponseEntity;

public interface TermsController {
    ResponseEntity<ResponseMessage> acceptTerms(String cpf);
}
