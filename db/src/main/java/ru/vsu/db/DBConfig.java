package ru.vsu.db;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan(basePackages = {
  "ru.vsu.db.entity"
})
@EnableJpaRepositories(basePackages = "ru.vsu.db.repository")
@EnableTransactionManagement
public class DBConfig {
}
