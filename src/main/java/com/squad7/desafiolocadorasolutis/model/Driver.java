package com.squad7.desafiolocadorasolutis.model;

import com.squad7.desafiolocadorasolutis.enums.AccountEmailStatusEnum;
import com.squad7.desafiolocadorasolutis.enums.SexEnum;
import com.squad7.desafiolocadorasolutis.exception.DriverMinorException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_driver")
public class Driver extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, unique = true, name = "cnh_number")
    private String cnhNumber;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "account_terms_id")
    private Terms accountTerms;

    @Enumerated(EnumType.STRING)
    private AccountEmailStatusEnum accountEmailStatusEnum;

    public Driver(String name, LocalDate birthDate, String cpf, String email, String cnhNumber, SexEnum sexEnum, AccountEmailStatusEnum accountEmailStatusEnum) {
        super(name, birthDate, cpf, sexEnum, email);
        this.cnhNumber = cnhNumber;
        this.accountEmailStatusEnum = accountEmailStatusEnum;
        this.accountTerms = new Terms(this);
    }

    public Driver acceptTerms() {
        this.getAccountTerms().acceptTerms();
        return this;
    }

    public void checkIfMinor() {
        LocalDate today = LocalDate.now();
        int age = Period.between(this.getBirthDate(), today).getYears();

        if (age < 18) {
            throw new DriverMinorException("The driver must be of legal age");
        }
    }

    protected Driver() {
        super();
    }
}
