package com.squad7.desafiolocadorasolutis.controller;

import com.squad7.desafiolocadorasolutis.controller.request.ClientPostRequest;
import com.squad7.desafiolocadorasolutis.controller.response.ResponseMessage;
import com.squad7.desafiolocadorasolutis.model.Client;
import com.squad7.desafiolocadorasolutis.service.impl.ClientServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientServiceImpl clientServiceImpl;

    @PostMapping
    public ResponseEntity<ResponseMessage> registerClient(@RequestBody ClientPostRequest clientRequest) {

        clientServiceImpl.registerClient(clientRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseMessage.builder()
                .code(HttpStatus.CREATED.value())
                .message("Client registered successfully")
                .build());
    }
}