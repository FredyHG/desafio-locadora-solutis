package com.squad7.desafiolocadorasolutis.model;

import com.squad7.desafiolocadorasolutis.enums.AccountEmailStatusEnum;
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

    private boolean blocked;
    @Enumerated(EnumType.STRING)
    private AccountEmailStatusEnum accountEmailStatusEnum;

    public Driver() {
        super();
    }
    public Driver(String name, LocalDate birthDate, String cpf, String email, String cnhNumber) {
        super(name, birthDate, cpf, email);
        this.cnhNumber = cnhNumber;
    }

    public Driver(Long id, String name, LocalDate birthDate, String cpf, String email, String cnhNumber, AccountEmailStatusEnum accountEmailStatusEnum) {
        super(name, birthDate, cpf, email);
        this.cnhNumber = cnhNumber;
        this.accountEmailStatusEnum = accountEmailStatusEnum;
    }

}
