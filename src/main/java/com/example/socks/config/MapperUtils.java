package com.example.socks.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperUtils {
    @Bean
    public ModelMapper getMapper(){
        return new ModelMapper();
    }
}
