package com.squad7.desafiolocadorasolutis.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Table(name = "tb_client")
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