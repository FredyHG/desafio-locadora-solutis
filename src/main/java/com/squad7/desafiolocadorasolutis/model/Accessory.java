package com.squad7.desafiolocadorasolutis.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_accessory")
public class Accessory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "accessories")
    private List<Car> cars;

    public Accessory(String name, String description) {
        this.name = name;
        this.description = description;
    }

    protected Accessory() {
    }
}
