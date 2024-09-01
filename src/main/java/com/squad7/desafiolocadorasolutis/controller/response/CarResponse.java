package com.squad7.desafiolocadorasolutis.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("license_plate")
    private String licensePlate;
    @JsonProperty("chassis")
    private String chassis;
    @JsonProperty("price_per_day")
    private BigDecimal pricePerDay;
    @JsonProperty("car_model")
    private CarModelDto carModel;
    @JsonProperty("accessories_details")
    private List<AccessoryResponseDto> accessories;
    @JsonProperty("transmission_type")
    private String transmissionType;
    @JsonProperty("fuel_type")
    private String fuelType;
    @JsonProperty("year_of_manufacture")
    private int yearOfManufacture;
    @JsonProperty("drive_type")
    private String driveType;
    @JsonProperty("mileage")
    private int mileage;
    @JsonProperty("color")
    private String color;
    @JsonProperty("image_url")
    private String imageURL;
}
