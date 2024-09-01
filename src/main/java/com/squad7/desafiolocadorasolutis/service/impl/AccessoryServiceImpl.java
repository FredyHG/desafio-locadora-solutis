package com.squad7.desafiolocadorasolutis.service.impl;

import com.squad7.desafiolocadorasolutis.model.Accessory;
import com.squad7.desafiolocadorasolutis.repository.AccessoryRepository;
import com.squad7.desafiolocadorasolutis.service.AccessoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccessoryServiceImpl implements AccessoryService {

    private final AccessoryRepository accessoryRepository;

    @Override
    public List<Accessory> findById(List<Accessory> list) {
        log.info("Starting search with ids for acessory list : {}", list);
        List<UUID> ids = list.stream()
                .map(Accessory::getId)
                .toList();

        log.info("Finished search with ids for accessory list : {}", ids);
        return accessoryRepository.findAllById(ids);
    }
}
