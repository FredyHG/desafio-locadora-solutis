package com.squad7.desafiolocadorasolutis.service.impl;

import com.squad7.desafiolocadorasolutis.model.CarModel;
import com.squad7.desafiolocadorasolutis.repository.CarModelRepository;
import com.squad7.desafiolocadorasolutis.service.CarModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CarModelServiceImpl implements CarModelService {

    private final CarModelRepository carModelRepository;

    @Override
    public CarModel findById(UUID id) {
        return carModelRepository.findById(id).orElseThrow(() -> new RuntimeException("Car Model not found"));
    }
}
