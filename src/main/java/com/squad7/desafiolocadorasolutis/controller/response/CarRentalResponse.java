package com.squad7.desafiolocadorasolutis.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.squad7.desafiolocadorasolutis.dto.*;
import com.squad7.desafiolocadorasolutis.enums.CarRentalStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Setter
@Getter
public class CarRentalResponse {
    private UUID id;
    private DriverResponseDto driver;
    @JsonProperty("rent_date")
    private LocalDate rentDate;
    @JsonProperty("return_date")
    private LocalDate returnDate;
    @JsonProperty("rental_status")
    private CarRentalStatusEnum rentalStatus;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("payment_details")
    private PaymentResponseDto payment;
    @JsonProperty("car")
    private CarResponseDto car;
    @JsonProperty("insurance_policy")
    private InsurancePolicyResponseDto insurancePolicy;
    @JsonProperty("rental_terms")
    private TermsResponseDto rentalTerms;
    @JsonProperty("employee")
    private EmployeeResponseDto employee;
}
