package com.example.socksmanager;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class SocksManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocksManagerApplication.class, args);
    }

}
