package com.squad7.desafiolocadorasolutis.controller.request;

import com.squad7.desafiolocadorasolutis.dto.AccessoryDTO;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class CarPostRequest {
    private String licensePlate;
    private String chassis;
    private BigDecimal pricePerDay;
    private List<AccessoryDTO> accessoriesIds;
    private String carModelId;
}
