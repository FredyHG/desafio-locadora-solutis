package com.squad7.desafiolocadorasolutis.repository;

import com.squad7.desafiolocadorasolutis.enums.CarRentalStatusEnum;
import com.squad7.desafiolocadorasolutis.enums.CategoryEnum;
import com.squad7.desafiolocadorasolutis.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {

    Optional<Car> findByChassis(String chassis);

    @Query("SELECT DISTINCT c FROM Car c " +
            "JOIN c.carModel cm " +
            "JOIN cm.manufacturer m " +
            "LEFT JOIN c.accessories a " +
            "JOIN CarRental cr ON cr.car = c " +
            "WHERE (:category IS NULL OR cm.categoryEnum = :category) " +
            "AND (:accessoryIds IS NULL OR a.id IN :accessoryIds) " +
            "AND (:rentalStatus IS NULL OR cr.rentalStatus = :rentalStatus)")
    List<Car> getAllCarsFiltered(@Param("category") CategoryEnum categoryEnum,
                                 @Param("accessoryIds") List<String> idsAccessories,
                                 @Param("rentalStatus") CarRentalStatusEnum rentalStatus);

    @Query(value = "SELECT c.* FROM tb_car c " +
            "JOIN tb_car_rental cr ON c.id = cr.car_id " +
            "WHERE c.id = :id " +
            "AND (:startRent BETWEEN cr.rent_date AND cr.return_date " +
            "OR :endRent BETWEEN cr.rent_date AND cr.return_date " +
            "OR cr.rent_date BETWEEN :startRent AND :endRent " +
            "OR cr.return_date BETWEEN :startRent AND :endRent)",
            nativeQuery = true)
    Optional<Car> findByCarUuidAndRentalPeriod(@Param("id") UUID id,
                                               @Param("startRent") LocalDate startRent,
                                               @Param("endRent") LocalDate endRent);
}