package org.top.productsandordersapiapp.rdb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.top.productsandordersapiapp.entity.ProductOrder;

@Repository
public interface ProductOrderRepository extends CrudRepository<ProductOrder, Integer> {
    Iterable<ProductOrder> findAllByProductId(Integer productId);

}
