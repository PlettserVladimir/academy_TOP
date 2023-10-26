package org.top.productsandordersapiapp.rdb.service;

import ch.qos.logback.core.joran.conditional.IfAction;
import org.springframework.stereotype.Service;
import org.top.productsandordersapiapp.entity.Client;
import org.top.productsandordersapiapp.entity.Product;
import org.top.productsandordersapiapp.rdb.repository.ClientRepository;
import org.top.productsandordersapiapp.service.ClientService;

import java.util.List;
import java.util.Optional;

@Service
public class RdbClientService implements ClientService {

    private final ClientRepository clientRepository;
    public RdbClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // клиентов можно добавить только в том случае, если имя и email уникальны
    @Override
    public Optional<Client> add(Client client) {
        // 1. проверить уникальность email-а, для этого надо попробовать найти клиента с таким email
        Optional<Client> existing = clientRepository.findByEmail(client.getEmail());
        if (existing.isPresent()) { // если есть клиент с таким email, то добавить нельзя
            // если добавить нельзя, то вернуть null, но возвращаем мы Optional
            return Optional.empty();
        }
        return Optional.of(clientRepository.save(client));
    }

    @Override
    public List<Client> getAll() {
        return (List<Client>)clientRepository.findAll();
    }

    @Override
    public Optional<Client> getById(Integer id) {
        return clientRepository.findById(id);
    }

    @Override
    public Boolean deleteById(Integer id) {
        Optional<Client> deleted = clientRepository.findById(id);
        if (deleted.isPresent()) {
            clientRepository.deleteById(id);
        }
        return deleted.isPresent();
    }

    @Override
    public Optional<Client> update(Client client) {
        Optional<Client> updated = clientRepository.findById(client.getId());
        if (updated.isPresent()) {
            if (client.getEmail().equals(updated.get().getEmail())) {
                return Optional.of(clientRepository.save(client));
            }
            else {
                Optional<Client> updatedMail = clientRepository.findByEmail(client.getEmail());
                if (updatedMail.isPresent()) {
                    return Optional.empty();
                }
                else {
                    return Optional.of(clientRepository.save(client));
                }
            }
        }
        return Optional.empty();
    }
}
