package com.squad7.desafiolocadorasolutis.repository;

import com.squad7.desafiolocadorasolutis.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
}