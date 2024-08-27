package com.squad7.desafiolocadorasolutis.exception.handlers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.squad7.desafiolocadorasolutis.controller.response.ErrorResponse;
import com.squad7.desafiolocadorasolutis.exception.PersonEmailAlreadyRegistred;
import com.squad7.desafiolocadorasolutis.exception.PersonException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class PersonExceptionHandler {

    private static final Map<String, HttpStatus> statusTable = new HashMap<>();

    @ExceptionHandler(PersonException.class)
    public ResponseEntity<ErrorResponse> handleCarException(PersonException ex) {
        log.error("Exception handled: {}", ex.getMessage());

        HttpStatus status = mapStatus(ex);

        ErrorResponse responseMessage = ErrorResponse.builder()
                .statusCode(status.value())
                .description(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(responseMessage, status);
    }

    private HttpStatus mapStatus(PersonException ex) {
        return statusTable.getOrDefault(ex.getClass().getSimpleName(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    static {
        statusTable.put(PersonEmailAlreadyRegistred.class.getSimpleName(), HttpStatus.CONFLICT);
    }
}
