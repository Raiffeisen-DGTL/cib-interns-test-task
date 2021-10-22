package com.github.matveyakulov.raifweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class RaifWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(RaifWebApplication.class, args);
    }

}
