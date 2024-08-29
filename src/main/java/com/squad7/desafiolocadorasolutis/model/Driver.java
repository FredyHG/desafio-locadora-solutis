package com.squad7.desafiolocadorasolutis.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_driver")
public class Driver extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String cnhNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "driver")
    private List<CarRental> carRentals;

    public Driver(String nome, LocalDate birthDate, String cpf, String email, String cnhNumber) {
        super(nome, birthDate, cpf, email);
        this.cnhNumber = cnhNumber;
    }

    protected Driver() {
        super();
    }

}
