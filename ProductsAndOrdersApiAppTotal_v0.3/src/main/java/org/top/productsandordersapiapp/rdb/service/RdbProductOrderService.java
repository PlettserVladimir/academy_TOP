package org.top.productsandordersapiapp.rdb.service;

import org.springframework.stereotype.Service;
import org.top.productsandordersapiapp.entity.Order;
import org.top.productsandordersapiapp.entity.Product;
import org.top.productsandordersapiapp.entity.ProductOrder;
import org.top.productsandordersapiapp.rdb.repository.ProductOrderRepository;
import org.top.productsandordersapiapp.service.OrderService;
import org.top.productsandordersapiapp.service.ProductOrderService;
import org.top.productsandordersapiapp.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class RdbProductOrderService implements ProductOrderService {

    // внедрение репоизторий в имплементацию сервиса через DI
    private final ProductOrderRepository productOrderRepository;

    //
    private final ProductService productService;
    private final OrderService orderService;

    public RdbProductOrderService(ProductOrderRepository productOrderRepository, ProductService productService,OrderService orderService) {
        this.productOrderRepository = productOrderRepository;
        this.productService = productService;
        this.orderService = orderService;
    }

    @Override
    public Optional<ProductOrder>  add(ProductOrder productOrder) {
        // заказ должен содержать id продукта
        Integer productId = productOrder.getProduct().getId();
        // 1. проверить - есть ли такой продукт
        Optional<Product> product = productService.getById(productId);
        Optional<Order> order = orderService.getById(productOrder.getOrder().getId());
        if (product.isEmpty()) {
            return Optional.empty();    // если нет такого продукта, то вернуть пустой результат
        }
        // 2. проверить, достаточно ли продукта
        if (product.get().getQuantity() < productOrder.getQuantity()) {
            return Optional.empty();    // если не достаточно на складе
        }
        // 3. если на складе достаточно, то изменить состояние товара (изменить количество) и сохранить новый заказ
        Product updatedProduct = product.get();
        Integer newProductQuantity = updatedProduct.getQuantity() - productOrder.getQuantity();
        updatedProduct.setQuantity(newProductQuantity);
        if (productService.update(updatedProduct).isEmpty()) {
            return Optional.empty();    // если не обновился продукт, то нельзя сохранить заказ
        }
        // 4. Получить сумму заказа и посчитать сумму всего чека, сохранить изменения в БД
        productOrder.setProduct(updatedProduct);
        //NEW. Получаем сумму заказа и сохраняем ее в поле SumProductOrder
        productOrder.setSumProductOrder(updatedProduct.getPrice()*productOrder.getQuantity());
        if (order.isPresent()) {
            //NEW. Получаем сумму чека(Order)
            double sumOrder = order.get().getSumOrder();
            //NEW. К сумме чека(SumProductOrder) прибавляем сумму нового заказа(SumProductOrder)
            sumOrder += productOrder.getSumProductOrder();
            //NEW. Записываем полученный результат в поле SumOrder
            order.get().setSumOrder(sumOrder);
            //NEW. Сохраняем изменения в БД
            orderService.update(order.get());
        }
        return Optional.of(productOrderRepository.save(productOrder));
    }

    @Override
    public List<ProductOrder> getAll() {
        return (List<ProductOrder>) productOrderRepository.findAll();
    }

    @Override
    public Optional<ProductOrder> getById(Integer id) {
        return productOrderRepository.findById(id);
    }

    @Override
    public List<ProductOrder> getAllByProductId(Integer productId) {
        return (List<ProductOrder>) productOrderRepository.findAllByProductId(productId);
    }
}
