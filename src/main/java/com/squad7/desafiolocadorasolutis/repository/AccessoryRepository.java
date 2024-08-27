package com.squad7.desafiolocadorasolutis.repository;

import com.squad7.desafiolocadorasolutis.model.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccessoryRepository extends JpaRepository<Accessory, UUID> {
}
