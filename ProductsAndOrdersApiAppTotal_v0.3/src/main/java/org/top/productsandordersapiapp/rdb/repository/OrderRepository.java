package org.top.productsandordersapiapp.rdb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.top.productsandordersapiapp.entity.Order;
import org.top.productsandordersapiapp.entity.ProductOrder;

import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
    Iterable<Order> findAllByClientId(Integer clientId);
}
