package com.squad7.desafiolocadorasolutis.service.payment;

import com.squad7.desafiolocadorasolutis.service.facade.PaymentMethod;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class PixPayment implements PaymentMethod {
    @Override
    public void processPayment(BigDecimal amount) {
        BigDecimal pixAmount = amount.multiply(BigDecimal.valueOf(0.1));
        BigDecimal total = amount.subtract(pixAmount);

        log.info("PixPayment processing amount {}", total);
    }
}
