package com.squad7.desafiolocadorasolutis.service.impl;

import com.squad7.desafiolocadorasolutis.controller.request.ClientCodeEmailValidationRequest;
import com.squad7.desafiolocadorasolutis.controller.request.ClientPostRequest;
import com.squad7.desafiolocadorasolutis.controller.request.ClientSendCodeEmailValidationRequest;
import com.squad7.desafiolocadorasolutis.controller.response.ResponseMessage;
import com.squad7.desafiolocadorasolutis.enums.AccountEmailStatusEnum;
import com.squad7.desafiolocadorasolutis.exception.ClientAlreadyExistsException;
import com.squad7.desafiolocadorasolutis.exception.ClientEmailAlreadyConfirmed;
import com.squad7.desafiolocadorasolutis.exception.ClientEmailCodeNotValid;
import com.squad7.desafiolocadorasolutis.exception.ClientNotFoundException;
import com.squad7.desafiolocadorasolutis.mappers.ClientMapper;
import com.squad7.desafiolocadorasolutis.model.Client;
import com.squad7.desafiolocadorasolutis.repository.ClientRepository;
import com.squad7.desafiolocadorasolutis.service.ClientService;
import com.squad7.desafiolocadorasolutis.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);
    private static final String EMAIL_VALIDATION_CODE = "3321";
    private final ClientRepository clientRepository;

    public void registerClient(ClientPostRequest clientPostRequest) {

        ensureClientNotRegisteredByCpf(clientPostRequest.getCpf());
        ensureClientNotRegisteredByEmail(clientPostRequest.getEmail());

        Client clientToBeSaved = ClientMapper.INSTANCE.requestToModel(clientPostRequest);
        clientToBeSaved.setAccountEmailStatusEnum(AccountEmailStatusEnum.TO_CONFIRM);

        clientToBeSaved = clientRepository.save(clientToBeSaved);
        logger.info(":: registerClient() - Client Saved: {} ::", clientToBeSaved);
    }

    @Override
    public void sendCodeToEmailValidation(ClientSendCodeEmailValidationRequest request) {
        Client client = getByEmail(request.getEmail());
        ensureClientEmailIsNotAlreadyConfirmed(client);
    }

    @Override
    public void validateCodeEmail(ClientCodeEmailValidationRequest request) {
        Client client = getByEmail(request.getEmail());
        ensureClientEmailIsNotAlreadyConfirmed(client);

        if (!request.getCode().equals(EMAIL_VALIDATION_CODE)){
            throw new ClientEmailCodeNotValid("Invalid email code.");
        }

        client.setAccountEmailStatusEnum(AccountEmailStatusEnum.CONFIRMED);
        clientRepository.save(client);
    }

    private Client getByEmail(String email){
        return clientRepository.findByEmail(email)
                .orElseThrow(() -> new ClientNotFoundException("No client found by email: "+email));
    }

    private void ensureClientNotRegisteredByEmail(String email) {
        if(clientRepository.findByEmail(email).isPresent()){
            throw new ClientAlreadyExistsException("Client with email " + email + " already exists.");
        }
    }

    private void ensureClientNotRegisteredByCpf(String cpf) {
        if (clientRepository.existsByCpf(cpf)) {
            throw new ClientAlreadyExistsException("Client with CPF " + cpf + " already exists.");
        }
    }

    @Override
    public Client findByCpf(String cpf) {
        return clientRepository
                .getByCpf(cpf)
                .orElseThrow(() -> new ClientNotFoundException("No client found by CPF: " + cpf));
    }

    @Override
    public Client getByCpfAndBlockedFalse(String cpf) {
        return clientRepository
                .getByCpfAndBlockedFalse(cpf)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }

    private void ensureClientEmailIsNotAlreadyConfirmed(Client client){
        if (client.getAccountEmailStatusEnum().equals(AccountEmailStatusEnum.CONFIRMED)){
            throw new ClientEmailAlreadyConfirmed("Client email already confirmed: "+client.getEmail());
        }
    }
}