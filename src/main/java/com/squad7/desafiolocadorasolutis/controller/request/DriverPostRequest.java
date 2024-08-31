package com.squad7.desafiolocadorasolutis.controller.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Getter
@Setter
public class DriverPostRequest {

    @NotBlank(message = "Name cannot be blank.")
    @Size(min = 3, message = "Name must be at least 3 characters long.")
    private String name;

    @Past(message = "Birth date must be in the past.")
    private LocalDate birthDate;

    @CPF(message = "CPF is invalid.")
    private String cpf;

    @Email(message = "Email is Invalid.")
    private String email;

    @NotBlank(message = "CNH number cannot be blank.")
    @Pattern(regexp = "^[0-9]{11}$", message = "CNH number must be 11 digits.")
    private String cnhNumber;
}