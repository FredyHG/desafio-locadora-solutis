package com.squad7.desafiolocadorasolutis.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CarModelDto {

    private UUID id;
    private String description;
    private ManufacturerDto manufacturer;
}
