package com.squad7.desafiolocadorasolutis.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_employee")
public class Employee extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String registration;

    public Employee(String nome, LocalDate birthDate, String cpf, String email, UUID id, String registration) {
        super(nome, birthDate,cpf, email);
        this.id = id;
        this.registration = registration;

    }


    public Employee() {
        super();
        this.id = null;
        this.registration = null;
    }
}
