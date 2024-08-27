package com.squad7.desafiolocadorasolutis.mappers;

import com.squad7.desafiolocadorasolutis.controller.request.ClientPostRequest;
import com.squad7.desafiolocadorasolutis.model.Client;

public class ClientMapper {
    public static Client requestToModel(ClientPostRequest clientPostRequest) {
        return new Client(clientPostRequest.getCnhNumber(),
                clientPostRequest.getEmail(),
                clientPostRequest.getCpf(),
                clientPostRequest.getBirthDate(),
                clientPostRequest.getName());
    }
}
