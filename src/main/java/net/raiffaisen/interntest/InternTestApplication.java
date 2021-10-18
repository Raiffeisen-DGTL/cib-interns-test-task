package net.raiffaisen.interntest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class InternTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(InternTestApplication.class, args);
	}

}
