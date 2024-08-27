package com.squad7.desafiolocadorasolutis.exception.handlers;

import com.squad7.desafiolocadorasolutis.controller.response.ResponseMessage;
import com.squad7.desafiolocadorasolutis.exception.ClientAlreadyExistsException;
import com.squad7.desafiolocadorasolutis.exception.ClientException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ClientExceptionHandler {

    private static final Map<String, HttpStatus> statusTable = new HashMap<>();

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<ResponseMessage> handleClientException(ClientException ex) {
        HttpStatus status = mapStatus(ex);
        return ResponseEntity.status(status).body(
                ResponseMessage.builder()
                        .code(status.value())
                        .message(ex.getMessage())
                        .build()
        );
    }

    private HttpStatus mapStatus(ClientException ex) {
        return statusTable.getOrDefault(ex.getClass().getSimpleName(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    static {
        // Adiciona mapeamentos de exceção para o status HTTP correspondente
        statusTable.put(ClientAlreadyExistsException.class.getSimpleName(), HttpStatus.CONFLICT);
        // Adicione outros mapeamentos de exceção aqui, se necessário
    }
}