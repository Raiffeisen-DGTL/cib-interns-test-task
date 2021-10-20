package com.raiffeisen.stocktaking.configuration;

import com.raiffeisen.stocktaking.model.SocksModel;
import com.raiffeisen.stocktaking.repository.AppRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public Map<String, BiFunction<String, Integer, Optional<Integer>>> operationMap(AppRepository repository) {
        return new HashMap<>() {{
            put("moreThan", repository::findSocksModelByColorAndCottonPartGreaterThan);
            put("lessThan", repository::findSocksModelByColorAndCottonPartLessThan);
            put("equal", repository::findSocksModelByColorAndQuantityEquals);
        }};
    }
}
