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
    @Column(name = "total_value", precision = 12, scale = 2)
    private BigDecimal totalValue;

    public InsurancePolicy(Boolean naturalCausesCoverage, Boolean thirdPartyCoverage, Boolean theftCoverage) {
        this.naturalCausesCoverage = naturalCausesCoverage;
        this.thirdPartyCoverage = thirdPartyCoverage;
        this.deductibleAmount = new BigDecimal(0);
        this.theftCoverage = theftCoverage;
        this.totalValue = new BigDecimal(0);
    }

    protected InsurancePolicy() {

    }

    public void calculatePolicyValue(BigDecimal periodValue) {
        addCoverageValue(naturalCausesCoverage, periodValue, BigDecimal.valueOf(0.03));
        addCoverageValue(theftCoverage, periodValue, BigDecimal.valueOf(0.1));
        addCoverageValue(thirdPartyCoverage, periodValue, BigDecimal.valueOf(0.05));

        calculateDeductibleAmount();
    }

    private void addCoverageValue(Boolean coverage, BigDecimal periodValue, BigDecimal multiplier) {
        if (Boolean.TRUE.equals(coverage)) {
            this.totalValue = this.totalValue.add(periodValue.multiply(multiplier));
        }
    }

    private void calculateDeductibleAmount() {
        this.deductibleAmount = this.deductibleAmount.add(this.totalValue.multiply(BigDecimal.valueOf(0.2)));
    }
}
