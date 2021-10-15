package com.oleg.socks.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource.manual")
@Getter
@Setter
public class DataSourceProp {

    private String driverClassName;
    private String jdbcUrl;
    private String username;
    private String password;
    private int idleTimeout;
    private int maximumPoolSize;
    private int minimumIdle;
}
