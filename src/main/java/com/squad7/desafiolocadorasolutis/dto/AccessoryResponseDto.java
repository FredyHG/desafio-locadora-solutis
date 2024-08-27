package com.squad7.desafiolocadorasolutis.dto;

import com.squad7.desafiolocadorasolutis.model.Car;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class AccessoryResponseDto {

    private UUID id;
    private String name;
    private String description;
}
