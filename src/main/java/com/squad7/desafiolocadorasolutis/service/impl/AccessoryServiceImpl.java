package com.squad7.desafiolocadorasolutis.service.impl;

import com.squad7.desafiolocadorasolutis.exception.AccessoryNotFoundException;
import com.squad7.desafiolocadorasolutis.model.Accessory;
import com.squad7.desafiolocadorasolutis.repository.AccessoryRepository;
import com.squad7.desafiolocadorasolutis.service.AccessoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccessoryServiceImpl implements AccessoryService {

    private final AccessoryRepository accessoryRepository;

    @Override
    public Accessory findById(UUID id) {
        return accessoryRepository.findById(id).orElseThrow(() -> new AccessoryNotFoundException("Accessory not found"));
    }
}
