package com.squad7.desafiolocadorasolutis.service;

import com.squad7.desafiolocadorasolutis.model.Accessory;

import java.util.List;
import java.util.UUID;

public interface AccessoryService {
    List<Accessory> findById(List<Accessory> list);
}
