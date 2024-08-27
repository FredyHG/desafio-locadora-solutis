package com.squad7.desafiolocadorasolutis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ClientExceptionHandler {

    private static final Map<String, HttpStatus> statusTable = new HashMap<>();

    static {
        statusTable.put(ClientAlreadyExistsException.class.getSimpleName(), HttpStatus.CONFLICT);
    }

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
}