package com.squad7.desafiolocadorasolutis.service.impl;

import com.squad7.desafiolocadorasolutis.model.Client;
import com.squad7.desafiolocadorasolutis.repository.ClientRepository;
import com.squad7.desafiolocadorasolutis.service.ClientService;
import com.squad7.desafiolocadorasolutis.service.TermsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TermsServiceImpl implements TermsService {

    private final ClientService clientService;
    private final ClientRepository clientRepository;

    @Override
    public void acceptTerms(String cpf) {
        Client client = clientService.findByCpf(cpf);
        client.acceptTerms();
        clientRepository.save(client);
    }
}
