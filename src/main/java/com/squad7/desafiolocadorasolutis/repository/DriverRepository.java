package com.squad7.desafiolocadorasolutis.repository;

import com.squad7.desafiolocadorasolutis.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findByEmail(String email);

    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);

    Optional<Driver> getByCpf(String cpf);

    Optional<Driver> getByCpfAndBlockedFalse(String cpf);


}
