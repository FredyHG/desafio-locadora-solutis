package com.squad7.desafiolocadorasolutis.service;

import com.squad7.desafiolocadorasolutis.controller.request.ClientCodeEmailValidationRequest;
import com.squad7.desafiolocadorasolutis.controller.request.ClientPostRequest;
import com.squad7.desafiolocadorasolutis.model.Client;
import com.squad7.desafiolocadorasolutis.controller.request.ClientSendCodeEmailValidationRequest;

public interface ClientService {
    void registerClient(ClientPostRequest clientPostRequest);
    Client findByCpf(String cpf);

    Client getByCpfAndBlockedFalse(String cpf);


    void sendCodeToEmailValidation(ClientSendCodeEmailValidationRequest request);
    void validateCodeEmail(ClientCodeEmailValidationRequest request);
}
