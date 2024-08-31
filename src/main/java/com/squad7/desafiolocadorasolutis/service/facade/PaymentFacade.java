package com.squad7.desafiolocadorasolutis.service.facade;

import java.math.BigDecimal;

public interface PaymentFacade {
    void makePayment(String paymentMethod, BigDecimal amount);
}
