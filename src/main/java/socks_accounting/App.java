package socks_accounting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackageClasses = { App.class })
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
