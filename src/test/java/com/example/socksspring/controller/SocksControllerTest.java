package com.example.socksspring.controller;

import com.example.socksspring.Socks;
import io.swagger.v3.oas.annotations.Operation;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.is;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SocksControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private SocksController controller;

    @Autowired
    private TestRestTemplate restTemplate;

    @Operation(summary = "При правильно выполненном запросе возвращается строка с номеров носков, " +
            "в тесте проверяется соответствие результата строке'")
    @Test
    public void addSocksValid()  {
        restTemplate.postForEntity("http://localhost:" + port + "/api/socks/income", new Socks(null, "red", 15, 5), String.class);
        ResponseEntity<String> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/socks?color=red&operation=lessThan&cottonPart=100", String.class
        );

        Assertions.assertTrue(response.hasBody());
    }

    @Operation(summary = "При ошибке в формате параметров возвращается код 400 в виде json, который" +
            "в тесте конвертируется в hashmap и проверяется ошбика 'Bad Request'")
    @Test
    public void addSocksInvalid(){
        restTemplate.postForEntity("http://localhost:" + port + "/api/socks/income", new Socks(null, "red", 15, 5), String.class);
        ResponseEntity<Object> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/socks?color=red&operation=lessThdsdsan&cottonPart=100", Object.class
        );

        MatcherAssert.assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }

    @Operation(summary = "Выполняется запрос на носки с содержанием хлопка больше 101%," +
            " которых по правилам в базе не может быть'")
    @Test
    public void addSocks404()  {
        ResponseEntity<Object> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/socks?color=red&operation=moreThan&cottonPart=101", Object.class
        );
        MatcherAssert.assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Проверка на отпуск носков")
    @Test
    public void removeSocks(){
        restTemplate.postForEntity("http://localhost:" + port + "/api/socks/income", new Socks(null, "red", 15, 5), String.class);
        ResponseEntity<String> response = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/socks/outcome", new Socks(null, "red", 15, 5),
                String.class
        );
        System.out.println(response.getStatusCode());
        MatcherAssert.assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }

}
