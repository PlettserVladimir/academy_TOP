package org.top.productsandordersapiapp.rdb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.top.productsandordersapiapp.entity.Client;

import java.util.Optional;

@Repository
public interface ClientRepository  extends CrudRepository<Client, Integer> {
    Optional<Client> findByEmail(String email);
}
