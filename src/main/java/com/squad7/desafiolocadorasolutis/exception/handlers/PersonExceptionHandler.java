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
        statusTable.put(PersonEmailAlreadyRegistered.class.getSimpleName(), HttpStatus.CONFLICT);
        statusTable.put(DriverAlreadyExistsException.class.getSimpleName(), HttpStatus.CONFLICT);
        statusTable.put(DriverNotFoundException.class.getSimpleName(), HttpStatus.NOT_FOUND);
        statusTable.put(DriverEmailAlreadyConfirmed.class.getSimpleName(), HttpStatus.CONFLICT);
        statusTable.put(InvalidEmailCodeException.class.getSimpleName(), HttpStatus.BAD_REQUEST);
        statusTable.put(DriverMinorException.class.getSimpleName(), HttpStatus.BAD_REQUEST);
        statusTable.put(EmployeeNotFoundException.class.getSimpleName(), HttpStatus.NOT_FOUND);
    }
}
