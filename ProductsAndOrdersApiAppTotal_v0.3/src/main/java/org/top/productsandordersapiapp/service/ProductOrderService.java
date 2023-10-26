package org.top.productsandordersapiapp.service;

import org.springframework.stereotype.Service;
import org.top.productsandordersapiapp.entity.ProductOrder;

import java.util.List;
import java.util.Optional;

// интерфейс (контракт) описывает операции с сущностью ProductOrder
@Service
public interface ProductOrderService {
    // ОСНОВНЫЕ МЕТОДЫ
    Optional<ProductOrder> add(ProductOrder productOrder); // Здесь будет доп. бизнес логика
    List<ProductOrder> getAll();
    Optional<ProductOrder> getById(Integer id);

    // ДОП БИЗНЕС ЛОГИКА
    List<ProductOrder> getAllByProductId(Integer productId);
}
