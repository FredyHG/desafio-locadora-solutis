package com.squad7.desafiolocadorasolutis.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AccessoryResponseDto {

    private UUID id;
    private String name;
    private String description;
}
