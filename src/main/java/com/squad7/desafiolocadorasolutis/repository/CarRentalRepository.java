package com.squad7.desafiolocadorasolutis.repository;

import com.squad7.desafiolocadorasolutis.enums.CarRentalStatusEnum;
import com.squad7.desafiolocadorasolutis.model.CarRental;
import com.squad7.desafiolocadorasolutis.model.Driver;
import com.squad7.desafiolocadorasolutis.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CarRentalRepository extends JpaRepository<CarRental, UUID> {

    @Query("SELECT c FROM CarRental c WHERE " +
            "(:employee IS NULL OR c.employee = :employee) AND " +
            "(:driver IS NULL OR c.driver = :driver) AND " +
            "(:carRentalStatusEnums IS NULL OR c.rentalStatus IN :carRentalStatusEnums)")
    List<CarRental> findByFilters(
            @Param("employee") Employee employee,
            @Param("driver") Driver driver,
            @Param("carRentalStatusEnums") List<CarRentalStatusEnum> carRentalStatusEnums
    );}
