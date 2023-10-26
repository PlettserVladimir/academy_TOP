package org.top.productsandordersapiapp.service;

import org.springframework.stereotype.Service;
import org.top.productsandordersapiapp.entity.Client;

import java.util.List;
import java.util.Optional;

// интерфейс (контракт) описывает операции с сущностью Client
@Service
public interface ClientService {
    // 1. добавить клиента
    Optional<Client> add(Client client);
    // 2. получить все записи
    List<Client> getAll();
    // 3. получить клиента по id
    Optional<Client> getById(Integer id);
    // 4. удалить клиента по id
    Boolean deleteById(Integer id);
    // 5. изменить поля клиента
    Optional<Client> update(Client client);
}
