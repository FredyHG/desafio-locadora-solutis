package com.squad7.desafiolocadorasolutis.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false, name = "license_plate")
    private String licensePlate;

    @Column(unique = true, nullable = false)
    private String chassis;

    @Column(nullable = false)
    private BigDecimal pricePerDay;

    @ManyToOne
    @JoinColumn(name = "car_model_id", nullable = false)
    private CarModel carModel;

    @ManyToMany
    @JoinTable(
            name = "car_accessory",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "accessory_id")
    )
    private List<Accessory> accessories;

    protected Car() {
    }

    public Car(UUID id, BigDecimal pricePerDay, String chassis, String licensePlate, CarModel carModel, List<Accessory> accessories) {
        this.id = id;
        this.pricePerDay = pricePerDay;
        this.chassis = chassis;
        this.licensePlate = licensePlate;
        this.carModel = carModel;
        this.accessories = accessories;
    }
}