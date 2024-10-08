package com.squad7.desafiolocadorasolutis.mappers;

import com.squad7.desafiolocadorasolutis.controller.request.CarPostRequest;
import com.squad7.desafiolocadorasolutis.controller.response.CarResponse;
import com.squad7.desafiolocadorasolutis.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    @Mapping(source = "accessoriesIds", target = "accessories")
    @Mapping(source = "carModelId", target = "carModel.id")
    @Mapping(target = "id", ignore = true)
    Car requestToModel(CarPostRequest car);

    @Mapping(target = "accessories", source = "accessories")
    CarResponse modelToResponse(Car car);
}
