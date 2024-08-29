package com.squad7.desafiolocadorasolutis.repository;

import com.squad7.desafiolocadorasolutis.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);

    Optional<Client> getByCpf(String cpf);

    Optional<Client> getByCpfAndBlockedFalse(String cpf);
    Optional<Client> findByEmail(String email);
}