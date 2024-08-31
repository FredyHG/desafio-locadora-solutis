package com.squad7.desafiolocadorasolutis.controller.request;

import com.squad7.desafiolocadorasolutis.dto.InsurancePolicyDto;
import com.squad7.desafiolocadorasolutis.model.InsurancePolicy;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class CarRentalPostRequest {

    @NotBlank(message = "Driver CPF cannot be blank.")
    private String driverCpf;

    @NotNull(message = "Car ID cannot be null.")
    private UUID carId;

    @NotNull(message = "Rent date cannot be null.")
    private LocalDateTime rentDate;

    @NotNull(message = "Return date cannot be null.")
    private LocalDateTime returnDate;

    private InsurancePolicyDto insurancePolicy;
}

