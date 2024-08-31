package com.squad7.desafiolocadorasolutis.controller.response;

import com.squad7.desafiolocadorasolutis.dto.*;
import com.squad7.desafiolocadorasolutis.enums.CarRentalStatus;
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
    private LocalDate rentDate;
    private LocalDate returnDate;
    private CarRentalStatus rentalStatus;
    private BigDecimal price;
    private PaymentResponseDto payment;
    private CarResponseDto car;
    private InsurancePolicyResponseDto insurancePolicy;
    private TermsResponseDto rentalTerms;
    private EmployeeResponseDto employee;

}
