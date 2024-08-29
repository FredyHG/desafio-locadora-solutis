package com.squad7.desafiolocadorasolutis.exception.handlers;

import com.squad7.desafiolocadorasolutis.controller.response.ResponseMessage;
import com.squad7.desafiolocadorasolutis.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ClientExceptionHandler {
    Logger log = LoggerFactory.getLogger(ClientExceptionHandler.class);

    private static final Map<String, HttpStatus> statusTable = new HashMap<>();

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<ResponseMessage> handleClientException(ClientException ex) {
        log.error("Exception handled: {}", ex.getMessage());

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
        statusTable.put(ClientAlreadyExistsException.class.getSimpleName(), HttpStatus.CONFLICT);
        statusTable.put(ClientNotFoundException.class.getSimpleName(), HttpStatus.NOT_FOUND);
        statusTable.put(ClientEmailAlreadyConfirmed.class.getSimpleName(), HttpStatus.CONFLICT);
        statusTable.put(ClientEmailCodeNotValid.class.getSimpleName(), HttpStatus.BAD_REQUEST);
    }
}