package com.squad7.desafiolocadorasolutis.controller.response;

import com.squad7.desafiolocadorasolutis.enums.AccountEmailStatusEnum;
import com.squad7.desafiolocadorasolutis.enums.SexEnum;
import com.squad7.desafiolocadorasolutis.model.Terms;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class DriverResponse {

    private UUID id;
    private String name;
    private String cpf;
    private String email;
    private String cnhNumber;
    private LocalDate birthDate;
    private SexEnum sexEnum;
    private Terms accountTerms;
    private AccountEmailStatusEnum accountEmailStatusEnum;
}
