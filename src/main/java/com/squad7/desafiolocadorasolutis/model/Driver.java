package com.squad7.desafiolocadorasolutis.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
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

    public Driver(String nome, LocalDate birthDate, String cpf, String email, UUID id, String cnhNumber) {
        super();
        this.id = id;
        this.cnhNumber = cnhNumber;
    }

    public Driver() {
        super();
        this.id = null;
        this.cnhNumber = null;
    }
}
