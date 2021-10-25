package com.example.testsocks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class TestSocksApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestSocksApplication.class, args);
    }

}
