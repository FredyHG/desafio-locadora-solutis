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

    @Column(unique = true, nullable = false, name = "chassis")
    private String chassis;

    @Column(nullable = false, precision = 12, scale = 2, name = "price_per_day")
    private BigDecimal pricePerDay;

    @Column(nullable = false, name = "image_url")
    private String imageURL;

    @Column(nullable = false, name = "transmission_type")
    private String transmissionType;

    @Column(nullable = false, name = "fuel_type")
    private String fuelType;

    @Column(nullable = false, name = "year_of_manufacture")
    private int yearOfManufacture;

    @Column(nullable = false, name = "drive_type")
    private String driveType;

    @Column(nullable = false, name = "mileage")
    private int mileage;

    @Column(nullable = false, name = "color")
    private String color;

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

    public Car(BigDecimal pricePerDay, String chassis, String licensePlate, CarModel carModel,
               List<Accessory> accessories,String transmissionType,String fuelType, int yearOfManufacture,
               String driveType,int mileage,String color, String imageURL) {
        this.pricePerDay = pricePerDay;
        this.chassis = chassis;
        this.licensePlate = licensePlate;
        this.carModel = carModel;
        this.accessories = accessories;
        this.imageURL = imageURL;
        this.transmissionType = transmissionType;
        this.fuelType = fuelType;
        this.yearOfManufacture = yearOfManufacture;
        this.driveType = driveType;
        this.mileage = mileage;
        this.color = color;
    }

    protected Car() {
    }
}