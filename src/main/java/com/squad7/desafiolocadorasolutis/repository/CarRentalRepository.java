package com.squad7.desafiolocadorasolutis.repository;

import com.squad7.desafiolocadorasolutis.model.CarRental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarRentalRepository extends JpaRepository<CarRental, UUID> {
}
