package com.squad7.desafiolocadorasolutis.service.impl;

import com.squad7.desafiolocadorasolutis.exception.InvalidPaymentMethodException;
import com.squad7.desafiolocadorasolutis.service.facade.PaymentFacade;
import com.squad7.desafiolocadorasolutis.service.facade.PaymentMethod;
import com.squad7.desafiolocadorasolutis.service.payment.CreditCardPayment;
import com.squad7.desafiolocadorasolutis.service.payment.PayPalPayment;
import com.squad7.desafiolocadorasolutis.service.payment.PixPayment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class PaymentFacadeImpl implements PaymentFacade {
    private final Map<String, PaymentMethod> paymentMethods;

    public PaymentFacadeImpl() {
        this.paymentMethods = new HashMap<>();

        paymentMethods.put("creditcard", new CreditCardPayment());
        paymentMethods.put("paypal", new PayPalPayment());
        paymentMethods.put("pix", new PixPayment());
    }

    @Override
    public void makePayment(String paymentMethod, BigDecimal amount) {
        log.info(":: makePayment() - Attempting to make payment with method: {} ::, for amount: {} ::", paymentMethod, amount);
        PaymentMethod method = paymentMethods.get(paymentMethod.toLowerCase());

        if (method == null) {
            log.info(":: makePayment() - Payment refused due to invalid payment method.");
            throw new InvalidPaymentMethodException("Invalid payment method: " + paymentMethod);
        }

        log.info(":: makePayment() - Payment successful for ammount: {}, with: {}", amount, paymentMethod);
        method.processPayment(amount);
    }
}
