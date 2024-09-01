package com.squad7.desafiolocadorasolutis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class PaymentResponseDto {

    private UUID id;
    private String paymentMethod;
    private LocalDateTime orderDate;
    private LocalDateTime paymentDate;
}
