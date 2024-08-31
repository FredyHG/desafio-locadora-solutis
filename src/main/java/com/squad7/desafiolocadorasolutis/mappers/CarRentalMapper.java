package com.squad7.desafiolocadorasolutis.mappers;

import com.squad7.desafiolocadorasolutis.controller.request.CarRentalPostRequest;
import com.squad7.desafiolocadorasolutis.controller.response.CarRentalResponse;
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
    @Mapping(target = "price", ignore = true)
    @Mapping(source = "paymentType", target = "payment.paymentMethod")
    @Mapping(target = "insurancePolicy.id", ignore = true)
    @Mapping(target = "insurancePolicy.deductibleAmount", ignore = true)
    @Mapping(target = "insurancePolicy.totalValue", ignore = true)
    @Mapping(target = "rentalStatus", ignore = true)
    @Mapping(target = "employee", ignore = true)
    CarRental requestToModel(CarRentalPostRequest carRentalPostRequest);

    @Mapping(source = "car.carModel.description", target = "car.description")
    @Mapping(source = "car.carModel.manufacturer.name", target = "car.manufacturer")
    CarRentalResponse modelToResponse(CarRental carRental);
}
