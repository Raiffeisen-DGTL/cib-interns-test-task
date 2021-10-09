package ru.strelchm.raiffeisenchallenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class JpaAuditingConfiguration {
    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.of("chat-user"); // todo - на будещее: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#auditing.interfaces
    }
}