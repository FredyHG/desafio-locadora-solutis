package com.squad7.desafiolocadorasolutis.controller.impl;

import com.squad7.desafiolocadorasolutis.controller.TermsController;
import com.squad7.desafiolocadorasolutis.controller.response.ResponseMessage;
import com.squad7.desafiolocadorasolutis.service.TermsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/terms")
@RequiredArgsConstructor
public class TermsControllerImpl implements TermsController {

    private final TermsService termsService;

    @PostMapping("/accept/{cpf}")
    @Override
    public ResponseEntity<ResponseMessage> acceptTerms(@PathVariable String cpf) {
        termsService.acceptTerms(cpf);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseMessage
                .builder()
                .message("Terms accepted successfully")
                .code(200)
                .build());
    }
}
