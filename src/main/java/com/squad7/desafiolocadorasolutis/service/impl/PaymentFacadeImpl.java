package com.squad7.desafiolocadorasolutis.service.impl;

import com.squad7.desafiolocadorasolutis.exception.InvalidPaymentMethodException;
import com.squad7.desafiolocadorasolutis.service.facade.PaymentFacade;
import com.squad7.desafiolocadorasolutis.service.facade.PaymentMethod;
import com.squad7.desafiolocadorasolutis.service.payment.CreditCardPayment;
import com.squad7.desafiolocadorasolutis.service.payment.PayPalPayment;
import com.squad7.desafiolocadorasolutis.service.payment.PixPayment;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

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
    public BigDecimal makePayment(String paymentMethod, BigDecimal amount) {
        PaymentMethod method = paymentMethods.get(paymentMethod.toLowerCase());

        if (method == null) {
            throw new InvalidPaymentMethodException("Invalid payment method: " + paymentMethod);
        }

        return method.processPayment(amount);
    }
}
