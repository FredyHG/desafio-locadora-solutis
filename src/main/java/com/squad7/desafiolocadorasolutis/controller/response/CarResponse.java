package com.squad7.desafiolocadorasolutis.controller.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.squad7.desafiolocadorasolutis.dto.AccessoryRequestDTO;
import com.squad7.desafiolocadorasolutis.dto.AccessoryResponseDto;
import com.squad7.desafiolocadorasolutis.dto.CarModelDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarResponse {
    
    private UUID id;
    private String licensePlate;
    private String chassis;
    private BigDecimal pricePerDay;
    private CarModelDto carModel;
    private List<AccessoryResponseDto> accessories;
}
