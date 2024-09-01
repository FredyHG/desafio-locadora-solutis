package com.squad7.desafiolocadorasolutis.controller.response;

import com.squad7.desafiolocadorasolutis.enums.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeResponse {

    private UUID id;
    private String name;
    private String cpf;
    private String email;
    private SexEnum sexEnum;
    private LocalDate birthDate;
    private String registration;
}
