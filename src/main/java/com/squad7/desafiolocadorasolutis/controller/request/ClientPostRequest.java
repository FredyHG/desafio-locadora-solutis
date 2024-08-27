package com.squad7.desafiolocadorasolutis.controller.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ClientPostRequest {
    private String name;
    private LocalDate birthDate;
    private String cpf;
    private String email;
    private String cnhNumber;
}