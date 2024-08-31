package com.squad7.desafiolocadorasolutis.controller.request;

import com.squad7.desafiolocadorasolutis.dto.AccessoryRequestDTO;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class CarPostRequest {

    @NotBlank(message = "License plate cannot be blank.")
    @Pattern(regexp = "[A-Z]{3}-\\d{4}", message = "License plate must be in the format ABC-1234.")
    private String licensePlate;

    @NotBlank(message = "Chassis number cannot be blank.")
    @Pattern(regexp = "[A-HJ-NPR-Z0-9]{17}", message = "Chassis number must be 17 characters long and exclude I, O, and Q.")
    private String chassis;

    @NotNull(message = "Price per day cannot be null.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price per day must be greater than 0.")
    private BigDecimal pricePerDay;

    @NotNull(message = "Accessories list cannot be null.")
    private List<AccessoryRequestDTO> accessoriesIds;

    @NotBlank(message = "Car model ID cannot be blank.")
    private String carModelId;

    @NotBlank(message = "URL of Car image cannot be blank.")
    private String imageURL;
}
