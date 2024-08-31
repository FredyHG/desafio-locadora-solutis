package com.squad7.desafiolocadorasolutis.model;

import com.squad7.desafiolocadorasolutis.enums.Sex;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@MappedSuperclass
public abstract class Person {
    private String name;
    private LocalDate birthDate;
    private String cpf;
    private String email;
    private Sex sex;

    public Person(String nome, LocalDate birthDate, String cpf,Sex sex, String email) {
        this.name = nome;
        this.birthDate = birthDate;
        this.cpf = cpf;
        this.sex = sex;
        this.email = email;
    }

    protected Person() {
    }
}
