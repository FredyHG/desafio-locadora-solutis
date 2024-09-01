package com.squad7.desafiolocadorasolutis.repository;

import com.squad7.desafiolocadorasolutis.enums.CarRentalStatusEnum;
import com.squad7.desafiolocadorasolutis.model.CarRental;
import com.squad7.desafiolocadorasolutis.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CarRentalRepository extends JpaRepository<CarRental, UUID> {
    List<CarRental> findByDriverAndRentalStatusIn(Driver driver, List<CarRentalStatusEnum> carRentalStatusEnums);
}
