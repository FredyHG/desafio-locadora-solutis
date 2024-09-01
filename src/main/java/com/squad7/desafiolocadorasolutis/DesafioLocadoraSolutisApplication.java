package com.squad7.desafiolocadorasolutis;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "Locadora API", version = "0.1", description = "Routes documentations"))
@SpringBootApplication
public class DesafioLocadoraSolutisApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioLocadoraSolutisApplication.class, args);
    }

}
