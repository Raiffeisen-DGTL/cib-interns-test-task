package ru.raiffeisen.shopwarehouse;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.raiffeisen.shopwarehouse.utils.DataBaseCheckerUtil;

import javax.sql.DataSource;

@SpringBootApplication
public class ShopWarehouseApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ShopWarehouseApplication.class, args);
        DataSource dataSource = context.getBean(javax.sql.DataSource.class);
        DataBaseCheckerUtil.connectionDataBase(dataSource);
    }

}
