package com.squad7.desafiolocadorasolutis.service.impl;

import com.squad7.desafiolocadorasolutis.exception.AccessoryNotFoundException;
import com.squad7.desafiolocadorasolutis.model.Accessory;
import com.squad7.desafiolocadorasolutis.repository.AccessoryRepository;
import com.squad7.desafiolocadorasolutis.service.AccessoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccessoryServiceImpl implements AccessoryService {

    private final AccessoryRepository accessoryRepository;
    @Override
    public Accessory findById(UUID id) {
        log.info("Starting search for accessory with id: {}", id);

        Accessory accessory = accessoryRepository.findById(id)
                .orElseThrow(() -> new AccessoryNotFoundException("Accessory not found"));

        log.info("Accessory found: ID = {}, Name = {}", accessory.getId(), accessory.getName());

        return accessory;
    }
}
