package com.squad7.desafiolocadorasolutis.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ErrorResponse {
    @JsonProperty("status_code")
    private Integer statusCode;
    private String description;
    private LocalDateTime timestamp;
}
