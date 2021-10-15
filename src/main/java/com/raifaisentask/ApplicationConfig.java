package com.raifaisentask;

import com.raifaisentask.data.Socks;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class ApplicationConfig {


/*
    @Bean
    public SpitterService spitterService(){
        return new SpitterServiceImpl();
    }
*/

    @Bean
    public DataSource dataSource() throws IOException {
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setDriverClassName(org.postgresql.Driver.class.getName());
        source.setUrl("jdbc:postgresql://localhost:5432/postgres");
        source.setUsername("postgres");
        source.setPassword("4HZdKL");
        Properties properties = new Properties();

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        properties.load(classloader.getResourceAsStream("application.properties"));
        source.setConnectionProperties(properties);
        return source;
    }

    @Bean
    public Socks socks(){
        return new Socks();
    }
    @Bean
    public JdbcTemplate jdbcTemplate() throws IOException {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public SocksDao socksDao() throws IOException {
        JdbsSocksDao socksDao = new JdbsSocksDao();
        socksDao.setJdbcTemplate(jdbcTemplate());
        return socksDao;
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
/*    @Bean
    public DataSourceTransactionManager transactionManager(){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource());
        return dataSourceTransactionManager;
    }*/
}
