package com.squad7.desafiolocadorasolutis.service.facade;

import java.math.BigDecimal;

public interface PaymentFacade {
    BigDecimal makePayment(String paymentMethod, BigDecimal amount);
}
