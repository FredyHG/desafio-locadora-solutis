package com.squad7.desafiolocadorasolutis.model;

import com.squad7.desafiolocadorasolutis.enums.CategoryEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_car_model")
public class CarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, name = "description")
    private String description;

    @Enumerated(EnumType.ORDINAL)
    private CategoryEnum categoryEnum;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    public CarModel(String description, CategoryEnum categoryEnum, Manufacturer manufacturer) {
        this.description = description;
        this.categoryEnum = categoryEnum;
        this.manufacturer = manufacturer;
    }

    protected CarModel() {
    }
}