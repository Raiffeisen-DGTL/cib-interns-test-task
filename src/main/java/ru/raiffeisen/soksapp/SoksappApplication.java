package ru.raiffeisen.soksapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("ru.raiffeisen.soksapp.repositary")
public class SoksappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoksappApplication.class, args);
	}

}
