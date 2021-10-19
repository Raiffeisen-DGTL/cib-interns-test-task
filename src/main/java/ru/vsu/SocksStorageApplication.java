package ru.vsu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.vsu")
public class SocksStorageApplication {
  public static void main(String[] args) {
    SpringApplication.run(SocksStorageApplication.class, args);
  }
}
