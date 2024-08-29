package com.squad7.desafiolocadorasolutis.controller;

import com.squad7.desafiolocadorasolutis.controller.request.ClientPostRequest;
import com.squad7.desafiolocadorasolutis.controller.response.ResponseMessage;
import com.squad7.desafiolocadorasolutis.model.Client;
import com.squad7.desafiolocadorasolutis.service.impl.ClientServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientServiceImpl clientServiceImpl;

    @PostMapping
    public ResponseEntity<ResponseMessage> registerClient(@RequestBody @Valid ClientPostRequest clientRequest) {

        log.info("Receive request to register new client with CPF: {}", clientRequest.getCpf());

        clientServiceImpl.registerClient(clientRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseMessage.builder()
                .code(HttpStatus.CREATED.value())
                .message("Client registered successfully")
                .build());
    }

    @GetMapping
    public ResponseEntity<Client> getByCpf(@RequestParam("cpf") String cpf){
        return new ResponseEntity(clientServiceImpl.getByCpfAndBlockedFalse(cpf), HttpStatus.OK);
    }

}