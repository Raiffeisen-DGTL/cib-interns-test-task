package ru.danilarassokhin.raiffeisensocks.config;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import ru.danilarassokhin.raiffeisensocks.apidocs.ErrorResponse;
import ru.danilarassokhin.raiffeisensocks.apidocs.SocksCountResponse;
import ru.danilarassokhin.raiffeisensocks.apidocs.SocksIncomeResponse;
import ru.danilarassokhin.raiffeisensocks.dto.SocksIncomeDto;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger configuration.
 */
@Configuration
@EnableSwagger2
public class SpringfoxConfiguration {
  @Bean
  public Docket api(TypeResolver typeResolver) {
    return new Docket(DocumentationType.SWAGGER_2)
        .useDefaultResponseMessages(false)
        .additionalModels(
            typeResolver.resolve(ErrorResponse.class),
            typeResolver.resolve(SocksIncomeDto.class),
            typeResolver.resolve(SocksIncomeResponse.class),
            typeResolver.resolve(SocksCountResponse.class)
        )
        .select()
        .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
        .paths(PathSelectors.ant("/api/**"))
        .build();
  }
}
