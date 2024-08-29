package com.squad7.desafiolocadorasolutis.service;

import com.squad7.desafiolocadorasolutis.controller.request.ClientCodeEmailValidationRequest;
import com.squad7.desafiolocadorasolutis.controller.request.ClientPostRequest;
import com.squad7.desafiolocadorasolutis.model.Client;
import com.squad7.desafiolocadorasolutis.controller.request.ClientSendCodeEmailValidationRequest;
import com.squad7.desafiolocadorasolutis.controller.response.ResponseMessage;

public interface ClientService {
    void registerClient(ClientPostRequest clientPostRequest);

    boolean existsByCpf(String cpf);

    void acceptTermsAndServices(String cpf);

    Client getByCpf(String cpf);

    Client getByCpfAndBlockedFalse(String cpf);


    void sendCodeToEmailValidation(ClientSendCodeEmailValidationRequest request);
    void validateCodeEmail(ClientCodeEmailValidationRequest request);
}
