package com.squad7.desafiolocadorasolutis.repository;

import com.squad7.desafiolocadorasolutis.enums.Category;
import com.squad7.desafiolocadorasolutis.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {

    Optional<Car> findByChassis(String chassis);

    @Query("SELECT DISTINCT c FROM Car c " +
            "JOIN c.carModel cm " +
            "JOIN cm.manufacturer m " +
            "LEFT JOIN c.accessories a " +
            "WHERE (:category IS NULL OR cm.category = :category) " +
            "AND (:accessoryIds IS NULL OR a.id IN :accessoryIds)")
    List<Car> getAllCarsFiltered(@Param("category") Category category,
                                 @Param("accessoryIds") List<String> idsAccessories);
}