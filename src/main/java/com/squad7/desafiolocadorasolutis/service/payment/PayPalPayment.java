package com.squad7.desafiolocadorasolutis.service.payment;

import com.squad7.desafiolocadorasolutis.service.facade.PaymentMethod;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class PayPalPayment implements PaymentMethod {

    @Override
    public void processPayment(BigDecimal amount) {
        BigDecimal paypalAmount = amount.multiply(BigDecimal.valueOf(0.05));
        BigDecimal total = amount.add(paypalAmount);

        log.info("Paypal processing amount {}", total);
    }
}
