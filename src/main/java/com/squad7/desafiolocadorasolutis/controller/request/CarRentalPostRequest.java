package com.squad7.desafiolocadorasolutis.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.squad7.desafiolocadorasolutis.dto.InsurancePolicyDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class CarRentalPostRequest {

    @NotBlank(message = "Driver CPF cannot be blank.")
    private String driverCpf;

    private String employeeRegistration;

    @NotNull(message = "Car ID cannot be null.")
    private UUID carId;

    @JsonFormat(pattern="yyyy-MM-dd")
    @NotNull(message = "Rent date cannot be null.")
    private LocalDate rentDate;

    @NotNull(message = "Return date cannot be null.")
    private LocalDate returnDate;

    @NotNull(message = "Insurance policy cannot be null")
    private InsurancePolicyDto insurancePolicy;

    @NotBlank(message = "Payment type cannot be null")
    private String paymentType;
}

