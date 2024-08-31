package com.squad7.desafiolocadorasolutis.service.payment;

import com.squad7.desafiolocadorasolutis.service.facade.PaymentMethod;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class PayPalPayment implements PaymentMethod {
    @Override
    public void processPayment(BigDecimal amount) {
        log.info("Payment with paypal processed successfully");
    }
}
