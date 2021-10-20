package com.raiffeisen.stocktaking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan(value = "com.raiffeisen.stocktaking.model")
public class StocktakingApplication {

    public static void main(String[] args) {
        SpringApplication.run(StocktakingApplication.class, args);
    }

}