package com.squad7.desafiolocadorasolutis.exception;

public class InvalidPaymentMethodException extends PaymentException {
    public InvalidPaymentMethodException(String msg) {
        super(msg);
    }
}
