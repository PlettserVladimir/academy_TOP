package org.top.productsandordersapiapp.rdb.service;

import org.springframework.stereotype.Service;
import org.top.productsandordersapiapp.entity.Client;
import org.top.productsandordersapiapp.entity.Order;
import org.top.productsandordersapiapp.rdb.repository.OrderRepository;
import org.top.productsandordersapiapp.service.ClientService;
import org.top.productsandordersapiapp.service.OrderService;


import java.util.List;
import java.util.Optional;


@Service
public class RdbOrderService implements OrderService {

    private final OrderRepository orderRepository;
    private final ClientService clientService;

    public RdbOrderService(OrderRepository orderRepository, ClientService clientService) {
        this.orderRepository = orderRepository;
        this.clientService = clientService;
    }

    // добавление заказа
    @Override
    public Optional<Order> add(Order order) {
        // достанем id клиента
        Integer clientId = order.getClient().getId();
        // попробуем найти клиента с таким id в базе
        Optional<Client> client = clientService.getById(clientId);
        //если клиент не пустой, то можно добавлять
        if (client.isPresent()) {
            return Optional.of(orderRepository.save(order));
        }
        return Optional.empty();
    }

    @Override
    public List<Order> getAll() {
        return (List<Order>)orderRepository.findAll() ;
    }

    // Метод получает заказ по id этого заказа
    // вход: id заказа
    // выход: найденный заказ, если есть заказ с таким id иначе null
    //NEW. Переделал весь метод getById()
    @Override
    public Optional<Order> getById(Integer id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> getAllByClientId(Integer clientId) {
        return (List<Order>) orderRepository.findAllByClientId(clientId);
    }

    @Override
    public Optional<Order> updateDescription(Integer id, String newDescription) {
        // получить заказ по ID
        Optional<Order> order = getById(id);
        if (order.isPresent()) {
            // установить новое описание
            order.get().setDescription(newDescription);
            // сохранить в БД
            orderRepository.save(order.get());
        }
        return order;
    }

    @Override
    public Optional<Order> update(Order order) {
        return Optional.of(orderRepository.save(order));
    }
}
