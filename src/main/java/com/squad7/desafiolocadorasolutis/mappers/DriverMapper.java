package com.squad7.desafiolocadorasolutis.mappers;

import com.squad7.desafiolocadorasolutis.controller.request.DriverPostRequest;
import com.squad7.desafiolocadorasolutis.model.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DriverMapper {

    DriverMapper INSTANCE = Mappers.getMapper(DriverMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "accountEmailStatusEnum", ignore = true)
    @Mapping(target = "accountTerms", ignore = true)
    Driver requestToModel(DriverPostRequest driverPostRequest);
}
