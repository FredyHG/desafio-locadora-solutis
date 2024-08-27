package com.squad7.desafiolocadorasolutis.service;

import com.squad7.desafiolocadorasolutis.model.Accessory;

import java.util.UUID;

public interface AccessoryService {
    Accessory findById(UUID id);
}
