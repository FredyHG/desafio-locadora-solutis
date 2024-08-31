package com.squad7.desafiolocadorasolutis.service.impl;

import com.squad7.desafiolocadorasolutis.exception.CarModelNotFoundException;
import com.squad7.desafiolocadorasolutis.model.CarModel;
import com.squad7.desafiolocadorasolutis.repository.CarModelRepository;
import com.squad7.desafiolocadorasolutis.service.CarModelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarModelServiceImpl implements CarModelService {

    private final CarModelRepository carModelRepository;

    @Override
    public CarModel findById(UUID id) {
        log.info("Starting search for car model with id: {}", id);

        CarModel carModel = carModelRepository.findById(id)
                .orElseThrow(() -> new CarModelNotFoundException("Car Model not found"));

        log.info("Car Model found: ID = {}, Description = {}", carModel.getId(), carModel.getDescription());

        return carModel;
    }
}
