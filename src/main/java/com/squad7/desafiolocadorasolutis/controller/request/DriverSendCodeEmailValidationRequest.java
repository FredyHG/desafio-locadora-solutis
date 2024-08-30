package com.squad7.desafiolocadorasolutis.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DriverSendCodeEmailValidationRequest {

    @NotBlank(message = "email cannot be blank")
    @Email(message = "email must be valid")
    private String email;
}
