package com.JavaBootcamp.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(SocksRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Socks(23, (byte) 40,"blue")));
            log.info("Preloading " + repository.save(new Socks(43, (byte) 80, "grey")));
        };
    }
}