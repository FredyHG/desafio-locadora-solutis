package com.squad7.desafiolocadorasolutis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class CarResponseDto {
    private UUID id;
    private String licensePlate;
    private BigDecimal pricePerDay;
    private String color;
    private String description;
    private String manufacturer;
}
