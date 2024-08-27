package com.squad7.desafiolocadorasolutis.service;

import com.squad7.desafiolocadorasolutis.model.CarModel;

import java.util.UUID;

public interface CarModelService {

    CarModel findById(UUID id);

}
