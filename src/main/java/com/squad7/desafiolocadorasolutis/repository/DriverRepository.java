package com.squad7.desafiolocadorasolutis.repository;

import com.squad7.desafiolocadorasolutis.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findByEmail(String email);
    Optional<Driver> findByCpf(String cpf);
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
}
