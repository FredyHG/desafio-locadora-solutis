package com.squad7.desafiolocadorasolutis.mappers;

import com.squad7.desafiolocadorasolutis.controller.request.CarRentalPostRequest;
import com.squad7.desafiolocadorasolutis.model.CarRental;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarRentalMapper {

    CarRentalMapper INSTANCE = Mappers.getMapper(CarRentalMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "driverCpf", target = "driver.cpf")
    @Mapping(target = "rentalTerms", ignore = true)
    @Mapping(source = "carId", target = "car.id")
    CarRental requestToModel(CarRentalPostRequest carRentalPostRequest);


}
