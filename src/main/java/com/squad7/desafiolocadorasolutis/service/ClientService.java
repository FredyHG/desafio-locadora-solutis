package com.squad7.desafiolocadorasolutis.service;

import com.squad7.desafiolocadorasolutis.controller.request.ClientPostRequest;
import com.squad7.desafiolocadorasolutis.model.Client;

public interface ClientService {
    public void registerClient(ClientPostRequest clientPostRequest);

    boolean existsByCpf(String cpf);

    void acceptTermsAndServices(String cpf);

    Client getByCpf(String cpf);

    Client getByCpfAndBlockedFalse(String cpf);


}
