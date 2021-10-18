package com.vadim01er.taskraiffeisenbank;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class TaskRaiffeisenbankApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskRaiffeisenbankApplication.class, args);
    }

}
