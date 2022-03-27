package com.zpsx.cibinternstesttask;

import com.zpsx.cibinternstesttask.domain.SockStock;
import com.zpsx.cibinternstesttask.repo.SockStockRepository;
import com.zpsx.cibinternstesttask.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
@Sql(value = {"/query/add-stocks-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/query/add-stocks-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class PostOutcomeTest {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    SockStockRepository sockStockRepo;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void withdrawUniqueTypeSocks() throws Exception {
        String color = "green";
        byte cottonPart = 50;
        long quantity = 10;

        ResponseEntity<Object> result = TestUtils.postOutcome(color, cottonPart, quantity, restTemplate, randomServerPort);
        assertThat(result.getStatusCodeValue()).isEqualTo(400);
    }

    @Test
    void withdrawExistingTypeSocks() throws Exception {
        String color = "red";
        byte cottonPart = 50;
        long quantity = 1;

        ResponseEntity<Object> result = TestUtils.postOutcome(color, cottonPart, quantity, restTemplate, randomServerPort);
        assertThat(result.getStatusCodeValue()).isEqualTo(200);

        SockStock sockStock = sockStockRepo.findByColorAndCottonPart(color, cottonPart);
        assertThat(sockStock.getQuantity()).isEqualTo(10);
    }

    @Test
    void withdrawNegativeQuantity() throws Exception {
        String color = "red";
        byte cottonPart = 50;
        long quantity = -10;

        ResponseEntity<Object> result = TestUtils.postOutcome(color, cottonPart, quantity, restTemplate, randomServerPort);
        assertThat(result.getStatusCodeValue()).isEqualTo(400);
    }

    @Test
    void withdrawUpperCaseColorName() throws Exception {
        String color = "RED";
        byte cottonPart = 50;
        long quantity = 1;

        ResponseEntity<Object> result = TestUtils.postOutcome(color, cottonPart, quantity, restTemplate, randomServerPort);
        assertThat(result.getStatusCodeValue()).isEqualTo(200);

        SockStock sockStock = sockStockRepo.findByColorAndCottonPart(color.toLowerCase(Locale.ROOT), cottonPart);
        assertThat(sockStock.getQuantity()).isEqualTo(10);
    }

    @Test
    void withdrawCottonPartOutOfBounds() throws Exception {
        String color = "red";
        byte cottonPart = 101;
        long quantity = 10;

        ResponseEntity<Object> result = TestUtils.postOutcome(color, cottonPart, quantity, restTemplate, randomServerPort);
        assertThat(result.getStatusCodeValue()).isEqualTo(400);
    }

    @Test
    void overdrawExistingTypeSocks() throws Exception {
        String color = "red";
        byte cottonPart = 50;
        long quantity = 1000;

        ResponseEntity<Object> result = TestUtils.postOutcome(color, cottonPart, quantity, restTemplate, randomServerPort);
        assertThat(result.getStatusCodeValue()).isEqualTo(400);
    }
}
