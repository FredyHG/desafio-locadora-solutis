package com.squad7.desafiolocadorasolutis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class InsurancePolicyResponseDto {

    private UUID id;
    private BigDecimal deductibleAmount;
    private Boolean thirdPartyCoverage;
    private Boolean naturalCausesCoverage;
    private Boolean theftCoverage;
    private BigDecimal totalValue;
}
