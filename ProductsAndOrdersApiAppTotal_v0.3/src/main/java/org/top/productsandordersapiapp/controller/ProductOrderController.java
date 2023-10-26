package org.top.productsandordersapiapp.controller;

import org.springframework.web.bind.annotation.*;
import org.top.productsandordersapiapp.entity.ProductOrder;
import org.top.productsandordersapiapp.service.ProductOrderService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("product-order")
public class ProductOrderController {

    // сервис работы с заказами на продукты
    private final ProductOrderService productOrderService;

    public ProductOrderController(ProductOrderService productOrderService) {
        this.productOrderService = productOrderService;
    }

    // 1. обработчики получения всех записей и получения по id
    @GetMapping("")
    public List<ProductOrder> findAll() {
        return productOrderService.getAll();
    }

    @GetMapping("{id}")
    public Optional<ProductOrder> findById(@PathVariable Integer id) {
        return productOrderService.getById(id);
    }

    // 2. добавление
    @PostMapping("new")
    public Optional<ProductOrder> addNew(@RequestBody ProductOrder productOrder) {
        return productOrderService.add(productOrder);
    }

    // 3. получение всех заказов на продукт
    @GetMapping("product/{productId}")
    public List<ProductOrder> findAllByProductId(@PathVariable Integer productId) {
        return productOrderService.getAllByProductId(productId);
    }
}
