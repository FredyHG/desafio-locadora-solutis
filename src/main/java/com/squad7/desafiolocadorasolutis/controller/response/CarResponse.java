package com.squad7.desafiolocadorasolutis.controller.response;

import com.squad7.desafiolocadorasolutis.dto.AccessoryResponseDto;
import com.squad7.desafiolocadorasolutis.dto.CarModelDto;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarResponse {

    private UUID id;
    private String licensePlate;
    private String chassis;
    private BigDecimal pricePerDay;
    private CarModelDto carModel;
    private List<AccessoryResponseDto> accessories;
    private String transmissionType;
    private String fuelType;
    private int yearOfManufacture;
    private String driveType;
    private int mileage;
    private String color;
    private String imageURL;
}
