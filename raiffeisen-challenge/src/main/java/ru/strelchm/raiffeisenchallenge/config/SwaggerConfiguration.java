package ru.strelchm.raiffeisenchallenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

@EnableSwagger2
@Configuration
//@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {
    @Bean
    public Docket productApi() {
        final List<ResponseMessage> responseMessages = new ArrayList<>();
        responseMessages.add(new ResponseMessageBuilder().code(500).message("INTERNAL_ERROR").responseModel(new ModelRef("ErrorResponse"))
                .build());
        responseMessages.add(new ResponseMessageBuilder().code(400).message("INVALID_PARAMETERS").responseModel(new ModelRef("ErrorResponse"))
                .build());
        return new Docket(DocumentationType.SWAGGER_2)
                .ignoredParameterTypes(Optional.class)
                .ignoredParameterTypes(Locale.class)
                .select().apis(RequestHandlerSelectors.basePackage("ru.strelchm.raiffeisenchallenge"))
                .build().useDefaultResponseMessages(false)
                .apiInfo(metaData()).useDefaultResponseMessages(false).directModelSubstitute(ResponseEntity.class, Void.class);
    }


    private ApiInfo metaData() {
        return new ApiInfo("Sock REST API", "Test REST API for socks", "1.0", null, null, "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
    }
}