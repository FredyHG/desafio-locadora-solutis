package com.squad7.desafiolocadorasolutis.model;

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
}
