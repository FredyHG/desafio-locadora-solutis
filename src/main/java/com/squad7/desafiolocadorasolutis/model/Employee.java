package com.squad7.desafiolocadorasolutis.model;

import com.squad7.desafiolocadorasolutis.enums.Sex;
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

    public Employee(String nome, LocalDate birthDate, String cpf, Sex sex, String email, String registration) {
        super(nome, birthDate,cpf, sex, email);
        this.registration = registration;

    }

    protected Employee() {
        super();
    }
}
