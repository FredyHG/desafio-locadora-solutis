package com.squad7.desafiolocadorasolutis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate birthDate;
    private String cpf;
    private String email;
    private String cnhNumber;

    protected Client() {}

    public Client(String cnhNumber, String email, String cpf, LocalDate birthDate, String name) {
        this.cnhNumber = cnhNumber;
        this.email = email;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.name = name;
    }
}