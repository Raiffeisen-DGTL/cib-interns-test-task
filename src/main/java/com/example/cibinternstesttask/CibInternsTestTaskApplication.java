package com.example.cibinternstesttask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.example.cibinternstesttask.controller"})
public class CibInternsTestTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(CibInternsTestTaskApplication.class, args);
	}

}
