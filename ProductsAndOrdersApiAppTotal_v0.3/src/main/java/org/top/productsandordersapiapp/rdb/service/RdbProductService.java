package org.top.productsandordersapiapp.rdb.service;

import org.springframework.stereotype.Service;
import org.top.productsandordersapiapp.entity.Product;
import org.top.productsandordersapiapp.rdb.repository.ProductRepository;
import org.top.productsandordersapiapp.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class RdbProductService implements ProductService {

    // внедрение репоизторий в имплементацию сервиса через DI
    private final ProductRepository productRepository;

    public RdbProductService(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAll() {
        return (List<Product>)productRepository.findAll();
    }

    @Override
    public Optional<Product> getById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public Product add(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Boolean deleteById(Integer id) {
        Optional<Product> deleted = productRepository.findById(id);
        if (deleted.isPresent()) {
            productRepository.deleteById(id);
        }
        return deleted.isPresent();
    }

    @Override
    public Optional<Product> update(Product product) {
        Optional<Product> updated = productRepository.findById(product.getId());
        if (updated.isPresent()) {
            productRepository.save(product);
            return Optional.of(product);
        }
        return Optional.empty();
    }
}
