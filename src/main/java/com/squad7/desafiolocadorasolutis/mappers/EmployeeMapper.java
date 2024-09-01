package com.squad7.desafiolocadorasolutis.mappers;

import com.squad7.desafiolocadorasolutis.controller.response.EmployeeResponse;
import com.squad7.desafiolocadorasolutis.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    EmployeeResponse modelToResponse(Employee employee);
}
