package com.squad7.desafiolocadorasolutis.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_manufacturer")
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, name = "name")
    private String name;

    public Manufacturer(String name) {
        this.name = name;
    }

    protected Manufacturer() {
    }
}