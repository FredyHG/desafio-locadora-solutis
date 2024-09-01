package com.squad7.desafiolocadorasolutis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class EmployeeResponseDto {

    private UUID id;
    private String name;
    private String cpf;
    private String registration;
}
