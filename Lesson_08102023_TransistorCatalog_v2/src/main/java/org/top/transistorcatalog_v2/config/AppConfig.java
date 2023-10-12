package org.top.transistorcatalog_v2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.top.transistorcatalog_v2.rdb.RdbTransistorService;
import org.top.transistorcatalog_v2.rdb.TransistorRepository;
import org.top.transistorcatalog_v2.service.TransistorService;

@Configuration
public class AppConfig {
    @Bean
    public TransistorService transistorService(TransistorRepository repository){
        return new RdbTransistorService(repository);
    }
}
