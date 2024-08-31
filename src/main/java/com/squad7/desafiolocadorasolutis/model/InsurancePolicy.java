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
    @Column(name = "total_value")
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

    public void calculatePolicyValue(BigDecimal periodValue){

        if (this.naturalCausesCoverage.equals(true)){
            this.totalValue = totalValue.add(periodValue.multiply(BigDecimal.valueOf(0.03)));
        }
        if (this.theftCoverage.equals(true)){
            this.totalValue = totalValue.add(periodValue.multiply(BigDecimal.valueOf(0.1)));
        }
        if (this.thirdPartyCoverage.equals(true)){
            this.totalValue = totalValue.add(periodValue.multiply(BigDecimal.valueOf(0.05)));
        }

        calculateDeductibleAmount();
    }

    private void calculateDeductibleAmount(){
        this.deductibleAmount =  this.deductibleAmount.add(this.totalValue.multiply(BigDecimal.valueOf(0.2)));
    }
}
