package com.squad7.desafiolocadorasolutis.controller.impl;

import com.squad7.desafiolocadorasolutis.controller.ClientController;
import com.squad7.desafiolocadorasolutis.controller.request.ClientCodeEmailValidationRequest;
import com.squad7.desafiolocadorasolutis.controller.request.ClientPostRequest;
import com.squad7.desafiolocadorasolutis.controller.request.ClientSendCodeEmailValidationRequest;
import com.squad7.desafiolocadorasolutis.controller.response.ResponseMessage;
import com.squad7.desafiolocadorasolutis.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/client")
@RequiredArgsConstructor
public class ClientControllerImpl implements ClientController {

    private final ClientService clientService;

    @PostMapping
    @Override
    public ResponseEntity<ResponseMessage> registerClient(@RequestBody @Valid ClientPostRequest clientRequest) {

        log.info("Receive request to register new client with CPF: {}", clientRequest.getCpf());

        clientService.registerClient(clientRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseMessage.builder()
                .code(HttpStatus.CREATED.value())
                .message("Client registered successfully")
                .build());
    }

    @PostMapping("/code/email")
    @Override
    public ResponseEntity<?> sendCodeToEmailValidation(@RequestBody @Valid ClientSendCodeEmailValidationRequest clientCodeValidation) {
        log.info(":: sendCodeToEmailValidation() - Request: {}", clientCodeValidation);

        clientService.sendCodeToEmailValidation(clientCodeValidation);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/code/email/validate")
    @Override
    public ResponseEntity<?> validateCodeEmail(@RequestBody @Valid ClientCodeEmailValidationRequest clientCodeValidation) {
        log.info(":: validateCodeEmail() - Request: {}", clientCodeValidation);

        clientService.validateCodeEmail(clientCodeValidation);

        return ResponseEntity.noContent().build();
    }
}