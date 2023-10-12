package org.top.transistorcatalog_v2.rdb;

import org.springframework.stereotype.Service;
import org.top.transistorcatalog_v2.intity.Transistor;
import org.top.transistorcatalog_v2.service.TransistorService;

import java.util.List;
import java.util.Optional;

@Service
public class RdbTransistorService implements TransistorService {
    private final TransistorRepository repository;
    public RdbTransistorService(TransistorRepository repository){
        this.repository = repository;
    }
    @Override
    public List<Transistor> findAll() {
        return (List<Transistor>) repository.findAll();
    }

    @Override
    public Optional<Transistor> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Transistor newEntry(Transistor transistor) {
        return repository.save(transistor);
    }

    @Override
    public Boolean deleteById(Integer id) {
        Optional<Transistor>deleted = repository.findById(id);
        if (deleted.isPresent()){
            repository.deleteById(id);
        }
        return deleted.isPresent();
    }

    @Override
    public Optional<Transistor> update(Transistor transistor) {
        Optional<Transistor> updated = repository.findById(transistor.getId());
        if (updated.isPresent()){
            repository.save(transistor);
            return Optional.of(transistor);
        }
        return Optional.empty();
    }
}
