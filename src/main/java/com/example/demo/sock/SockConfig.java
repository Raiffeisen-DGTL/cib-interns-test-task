package com.example.demo.sock;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SockConfig {

    @Bean
    CommandLineRunner commandLineRunner(SockRepository repository) {
        return args -> {
            Sock black = new Sock(
                    "black",
                    (byte)18,
                    (short)5
            );
            Sock red = new Sock(
                    "red",
                    (byte)42,
                    (short)2
            );

            repository.saveAll(
                    List.of(black,red)
            );
        };
    }
}
