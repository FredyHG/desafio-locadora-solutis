package com.squad7.desafiolocadorasolutis.service.facade;

import java.math.BigDecimal;

public interface PaymentMethod {
    void processPayment(BigDecimal amount);
}
