package com.squad7.desafiolocadorasolutis.repository;

import com.squad7.desafiolocadorasolutis.model.InsurancePolicy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, UUID> {
}
