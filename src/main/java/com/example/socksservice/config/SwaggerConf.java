package com.example.socksservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConf{
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host("localhost:8081")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.socksservice.controller"))
                .paths(regex("/api/socks.*"))
                .build();

    }

}
