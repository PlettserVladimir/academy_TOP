package org.top.productsandordersapiapp.controller;

import org.springframework.web.bind.annotation.*;
import org.top.productsandordersapiapp.entity.Order;
import org.top.productsandordersapiapp.service.OrderService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // ОБРАБОТЧИКИ, ВЫЗЫВАЮЩИЕ ОПЕРАЦИИ ПО ВЗАИМОДЕЙСТВИЮ С БД
    // 1. добавить заказ
    @PostMapping("new")
    public Optional<Order> addNew(@RequestBody Order order) {
        return orderService.add(order);
    }

    // 2. получить все заказы
    @GetMapping("")
    public List<Order> findAll() {
        return orderService.getAll();
    }

    // 3. получить заказ по id заказа
    @GetMapping("{id}")
    public Optional<Order> findById(@PathVariable Integer id) {
        return orderService.getById(id);
    }

    // 4. получить заказы по id клиента
    @GetMapping("client/{id}")
    public List<Order> findByClientId(@PathVariable Integer id) {
        return orderService.getAllByClientId(id);
    }

    // 5. редактирование описания заказа
    @PostMapping("update/{id}")
    public Optional<Order> update(@PathVariable Integer id, @RequestParam String newDescription) {
        return orderService.updateDescription(id, newDescription);
    }
}
