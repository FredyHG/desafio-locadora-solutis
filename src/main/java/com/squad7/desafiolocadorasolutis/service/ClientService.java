package com.squad7.desafiolocadorasolutis.service;

import com.squad7.desafiolocadorasolutis.exception.ClientAlreadyExistsException;
import com.squad7.desafiolocadorasolutis.model.Client;
import com.squad7.desafiolocadorasolutis.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public void registerClient(Client client) {
        if (clientRepository.existsByCpf(client.getCpf())) {
            throw new ClientAlreadyExistsException("Client with CPF " + client.getCpf() + " already exists.");
        }
        if (clientRepository.existsByEmail(client.getEmail())) {
            throw new ClientAlreadyExistsException("Client with email " + client.getEmail() + " already exists.");
        }
        clientRepository.save(client);
    }
}