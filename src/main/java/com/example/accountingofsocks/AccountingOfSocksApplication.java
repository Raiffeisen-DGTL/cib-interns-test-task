package com.example.accountingofsocks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class AccountingOfSocksApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountingOfSocksApplication.class, args);
        Random random = new Random();

        Long regNumber = random.nextLong();
        System.out.println(regNumber);
    }

}
