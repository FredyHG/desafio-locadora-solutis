package com.squad7.desafiolocadorasolutis.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarModelDto {

    private UUID id;
    private String description;
    private ManufacturerDto manufacturer;
}
