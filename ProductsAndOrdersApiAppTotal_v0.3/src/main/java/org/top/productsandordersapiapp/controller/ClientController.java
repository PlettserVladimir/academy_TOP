package org.top.productsandordersapiapp.controller;

import org.springframework.web.bind.annotation.*;
import org.top.productsandordersapiapp.entity.Client;
import org.top.productsandordersapiapp.service.ClientService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // ОБРАБОТЧИКИ, ВЫЗЫВАЮЩИЕ ОПЕРАЦИИ ПО ВЗАИМОДЕЙСТВИЮ С БД
    // 1. добавить клиента
    @PostMapping("new")
    public Optional<Client> addNew(@RequestBody Client client) {
        return clientService.add(client);
    }

    // 2. получить все записи
    @GetMapping("")
    public List<Client> findAll() {
        return clientService.getAll();
    }

    // 3. получить клиента по id
    @GetMapping("{id}")
    public Optional<Client> findById(@PathVariable Integer id) {
        return clientService.getById(id);
    }

    // 4. удалить клиента по id
    @DeleteMapping("{id}")
    public Boolean deleteById(@PathVariable Integer id) {
        return clientService.deleteById(id);
    }

    // 5. изменить поля клиента
    @PostMapping("update")
    public Optional<Client> updateExisting(@RequestBody Client client) {
        return clientService.update(client);
    }
}
