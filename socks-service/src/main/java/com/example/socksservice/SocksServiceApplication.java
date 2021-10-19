package com.example.socksservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SocksServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocksServiceApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(SocksRepository repository) {
//        return args -> {
//            Sock red = new Sock("red", 13, 10);
//            repository.save(red);
//        };
//    }
}
