package com.squad7.desafiolocadorasolutis.service.impl;

import com.squad7.desafiolocadorasolutis.controller.request.ClientPostRequest;
import com.squad7.desafiolocadorasolutis.exception.ClientAlreadyExistsException;
import com.squad7.desafiolocadorasolutis.mappers.ClientMapper;
import com.squad7.desafiolocadorasolutis.model.Client;
import com.squad7.desafiolocadorasolutis.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl {


    private final ClientRepository clientRepository;

    public void registerClient(ClientPostRequest clientPostRequest) {

        ensureClientNotRegisteredByCpf(clientPostRequest.getCpf());
        ensureClientNotRegisteredByEmail(clientPostRequest.getEmail());

        Client clientToBeSaved = ClientMapper.INSTANCE.requestToModel(clientPostRequest);

        clientRepository.save(clientToBeSaved);
    }


    private void ensureClientNotRegisteredByEmail(String email) {
        if (clientRepository.existsByEmail(email)) {
            throw new ClientAlreadyExistsException("Client with email " + email + " already exists.");
        }
    }

    private void ensureClientNotRegisteredByCpf(String cpf) {
        if (clientRepository.existsByCpf(cpf)) {
            throw new ClientAlreadyExistsException("Client with CPF " + cpf + " already exists.");
        }
    }
}