package org.top.transistorcatalog_v2.rdb;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.top.transistorcatalog_v2.intity.Transistor;

@Repository
public interface TransistorRepository extends CrudRepository<Transistor,Integer> {
}
