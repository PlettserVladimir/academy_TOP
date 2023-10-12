package org.top.transistorcatalog_v2.service;

import org.springframework.stereotype.Service;
import org.top.transistorcatalog_v2.intity.Transistor;

import java.util.List;
import java.util.Optional;

@Service
public interface TransistorService {
    List<Transistor> findAll();
    Optional<Transistor> findById(Integer id);
    Transistor newEntry(Transistor transistor);
    Boolean deleteById(Integer id);
    Optional<Transistor> update(Transistor transistor);
}
