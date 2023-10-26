package org.top.productsandordersapiapp.service;

import org.springframework.stereotype.Service;
import org.top.productsandordersapiapp.entity.Order;

import java.util.List;
import java.util.Optional;

// интерфейс (контракт) описывает операции с сущностью Order
@Service
public interface OrderService {

    // 1. добавить новую запись
    Optional<Order> add(Order order);

    // 2. получить все заказы
    List<Order> getAll();

    // 3. получить заказ по id заказа
    Optional<Order> getById(Integer id);

    // 4. получить заказы по id клиента
    List<Order> getAllByClientId(Integer clientId);

    // 5. редактирование описания заказа
    Optional<Order> updateDescription(Integer id, String newDescription);

    //6. Изменения заказа
    Optional<Order> update (Order order);
}
