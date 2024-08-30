package com.squad7.desafiolocadorasolutis.model;

import com.squad7.desafiolocadorasolutis.enums.AccountEmailStatusEnum;
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
    private boolean blocked;

    @Enumerated(EnumType.STRING)
    private AccountEmailStatusEnum accountEmailStatusEnum;

    public Client acceptTerms() {
        if(!blocked) {
            throw new RuntimeException("Client already accepted");
        }

        this.blocked = false;
        return this;
    }

    protected Client() {}

    public Client(Long id, String name, LocalDate birthDate, String cpf, String email, String cnhNumber, AccountEmailStatusEnum accountEmailStatusEnum) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.cpf = cpf;
        this.email = email;
        this.cnhNumber = cnhNumber;
        this.accountEmailStatusEnum = accountEmailStatusEnum;
    }
}