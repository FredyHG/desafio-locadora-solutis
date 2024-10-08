package com.squad7.desafiolocadorasolutis.exception.handlers;

import com.squad7.desafiolocadorasolutis.controller.response.ErrorResponse;
import com.squad7.desafiolocadorasolutis.exception.InvalidPaymentMethodException;
import com.squad7.desafiolocadorasolutis.exception.PaymentException;
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
public class PaymentExceptionHandler {
    private static final Map<String, HttpStatus> statusTable = new HashMap<>();

    @ExceptionHandler(PaymentException.class)
    public ResponseEntity<ErrorResponse> handleCarException(PaymentException ex) {
        log.error("Exception handled: {}", ex.getMessage());

        HttpStatus status = mapStatus(ex);

        ErrorResponse responseMessage = ErrorResponse.builder()
                .statusCode(status.value())
                .description(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(responseMessage, status);
    }

    private HttpStatus mapStatus(PaymentException ex) {
        return statusTable.getOrDefault(ex.getClass().getSimpleName(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    static {
        statusTable.put(InvalidPaymentMethodException.class.getSimpleName(), HttpStatus.CONFLICT);
    }
}
