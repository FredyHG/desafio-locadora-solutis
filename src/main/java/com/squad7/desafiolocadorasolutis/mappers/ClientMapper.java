package com.squad7.desafiolocadorasolutis.mappers;

import com.squad7.desafiolocadorasolutis.controller.request.ClientPostRequest;
import com.squad7.desafiolocadorasolutis.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Mapping(target = "id", ignore = true)
    Client requestToModel(ClientPostRequest clientPostRequest);
}
