package org.top.transistorcatalog_v2.Controller;

import org.springframework.web.bind.annotation.*;
import org.top.transistorcatalog_v2.intity.Transistor;
import org.top.transistorcatalog_v2.service.TransistorService;

import java.util.List;
import java.util.Optional;

@RestController
public class TransistorCatalogController {
    private final TransistorService transistorService;
    public TransistorCatalogController(TransistorService transistorService){
        this.transistorService = transistorService;
    }

    @GetMapping("transistor/all")
    public List<Transistor> findAll(){
        return transistorService.findAll();
    }

    @GetMapping("transistor/findById")
    public Optional<Transistor> findById(@RequestParam Integer id){
        return transistorService.findById(id);
    }
    @PostMapping("transistor/new")
    public Transistor newEntry(@RequestBody Transistor transistor){
        return transistorService.newEntry(transistor);
    }
    @GetMapping("transistor/delete")
    public Boolean deleteEntry(@RequestParam Integer id){
        return transistorService.deleteById(id);
    }
    @PostMapping("transistor/update")
    public Optional<Transistor> update(@RequestBody Transistor transistor){
        return transistorService.update(transistor);
    }
}
