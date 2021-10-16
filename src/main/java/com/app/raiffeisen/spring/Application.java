package com.app.raiffeisen.spring;

import com.app.raiffeisen.database.DataBaseController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        DataBaseController controller = new DataBaseController();
        SpringApplication.run(Application.class, args);

    }

}
