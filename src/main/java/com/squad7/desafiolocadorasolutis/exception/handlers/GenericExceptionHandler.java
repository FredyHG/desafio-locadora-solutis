package com.squad7.desafiolocadorasolutis.exception.handlers;

import com.squad7.desafiolocadorasolutis.controller.response.ErrorResponse;
import com.squad7.desafiolocadorasolutis.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GenericExceptionHandler {

    private static final Map<String, HttpStatus> statusTable = new HashMap<>();

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleGenericException(IllegalArgumentException ex) {
        log.error("Exception handled: {}", ex.getMessage());


        ErrorResponse responseMessage = ErrorResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .description(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ErrorResponse> handleCarException(GenericException ex) {
        log.error("Exception handled: {}", ex.getMessage());

        HttpStatus status = mapStatus(ex);

        ErrorResponse responseMessage = ErrorResponse.builder()
                .statusCode(status.value())
                .description(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(responseMessage, status);
    }


    private HttpStatus mapStatus(GenericException ex) {
        return statusTable.getOrDefault(ex.getClass().getSimpleName(), HttpStatus.BAD_REQUEST);
    }

    static {
        statusTable.put(InvalidEmailCodeException.class.getSimpleName(), HttpStatus.CONFLICT);
        statusTable.put(InvalidStatusException.class.getSimpleName(), HttpStatus.CONFLICT);
        statusTable.put(EmailNotConfirmedException.class.getSimpleName(), HttpStatus.CONFLICT);
        statusTable.put(TermsNotAcceptedException.class.getSimpleName(), HttpStatus.CONFLICT);
    }
}
