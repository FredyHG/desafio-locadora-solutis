package com.squad7.desafiolocadorasolutis.repository;

import com.squad7.desafiolocadorasolutis.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, UUID> {
}
