package ru.samusev.api;


import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.transaction.Transactional;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Transactional
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration(initializers = AbstractTest.Initializer.class)
public class AbstractTest {

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        private PostgreSQLContainer postgres = new PostgreSQLContainer() {{
            withDatabaseName("rbtt");
            withUsername("rbtt");
            withPassword("rbtt");
        }};

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            postgres.start();

            System.setProperty("POSTGRES_URL", postgres.getJdbcUrl());
            System.setProperty("POSTGRES_USER", postgres.getUsername());
            System.setProperty("POSTGRES_PASSWORD", postgres.getPassword());
        }
    }
}
