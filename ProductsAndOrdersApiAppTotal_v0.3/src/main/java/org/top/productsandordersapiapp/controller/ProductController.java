package org.top.productsandordersapiapp.controller;

import org.springframework.web.bind.annotation.*;
import org.top.productsandordersapiapp.entity.Product;
import org.top.productsandordersapiapp.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // ОБРАБОТЧИКИ, ВЫЗЫВАЮЩИЕ ОПЕРАЦИИ ПО ВЗАИМОДЕЙСТВИЮ С БД
    // 1. получить все записи
    @GetMapping("")
    public List<Product> findAll() {
        return productService.getAll();
    }

    // 2. получить запись по id
    @GetMapping("{id}")
    public Optional<Product> findById(@PathVariable Integer id) {
        return productService.getById(id);
    }

    // 3. добавить новую запись
    @PostMapping("new")
    public Product addNew(@RequestBody Product product) {
        return productService.add(product);
    }

    // 4. удалить запись по id
    @DeleteMapping("{id}")
    public Boolean deleteById(@PathVariable Integer id) {
        return productService.deleteById(id);
    }

    // 5. изменить запись localhost:8080/product/update
    @PostMapping("update")
    public Optional<Product> updateExisting(@RequestBody Product product) {
        return productService.update(product);
    }
}
