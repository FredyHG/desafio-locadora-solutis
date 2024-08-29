package com.squad7.desafiolocadorasolutis.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Table(name = "tb_insurance_policy")
@Entity
public class InsurancePolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "deductible_amount")
    private BigDecimal deductibleAmount;
    @Column(name = "third_party_coverage")
    private Boolean thirdPartyCoverage;
    @Column(name = "natural_causes_coverage")
    private Boolean naturalCausesCoverage;
    @Column(name = "theft_coverage")
    private Boolean theftCoverage;

    public InsurancePolicy(Boolean naturalCausesCoverage, Boolean thirdPartyCoverage, BigDecimal deductibleAmount, Boolean theftCoverage) {
        this.naturalCausesCoverage = naturalCausesCoverage;
        this.thirdPartyCoverage = thirdPartyCoverage;
        this.deductibleAmount = deductibleAmount;
        this.theftCoverage = theftCoverage;
    }

    protected InsurancePolicy() {

    }
}
