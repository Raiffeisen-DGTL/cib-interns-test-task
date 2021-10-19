package com.raiffeisen.interntask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class InternsTestTask {

    public static void main(String[] args) {
        //AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        SpringApplication.run(InternsTestTask.class, args);
    }

}
