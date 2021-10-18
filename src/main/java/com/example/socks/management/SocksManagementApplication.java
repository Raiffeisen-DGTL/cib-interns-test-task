package com.example.socks.management;

import com.example.socks.management.dao.SocksDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
public class SocksManagementApplication {
	public static void main(String[] args) {
		run(SocksManagementApplication.class, args);
	}

}
