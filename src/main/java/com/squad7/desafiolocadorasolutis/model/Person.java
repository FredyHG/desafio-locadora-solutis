package com.squad7.desafiolocadorasolutis.model;

import com.squad7.desafiolocadorasolutis.enums.SexEnum;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@MappedSuperclass
public abstract class Person {
    @Column(nullable = false, name = "name")
    private String name;
    @Column(nullable = false, name = "birth_date")
    private LocalDate birthDate;
    @Column(nullable = false, unique = true, name = "cpf")
    private String cpf;
    @Column(nullable = false, unique = true, name = "email")
    private String email;
    @Column(nullable = false, name = "sex")
    private SexEnum sexEnum;

    public Person(String nome, LocalDate birthDate, String cpf, SexEnum sexEnum, String email) {
        this.name = nome;
        this.birthDate = birthDate;
        this.cpf = cpf;
        this.sexEnum = sexEnum;
        this.email = email;
    }

    protected Person() {
    }
}
