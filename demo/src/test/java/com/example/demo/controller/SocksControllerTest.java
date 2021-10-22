package com.example.demo.controller;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SocksControllerTest {

    @LocalServerPort
    private int port = 8080;

    @Autowired
    private TestRestTemplate restTemplate;

    //добавили, посмотрели, столько же отняли - всегда ожидаем статус код 200 (да, колхоз)

    @ParameterizedTest
    @ValueSource(strings = {"color=yellow&cottonPart=10&quantity=8",
            "color=white&cottonPart=60&quantity=88",
            "color=black&cottonPart=100&quantity=96"
    })
    void income(String params) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://localhost:" + port + "/api/socks/income?" + params,
                HttpMethod.POST,
                null,
                String.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"color=yellow&operation=equal&cottonPart=10",
            "color=white&operation=equal&cottonPart=60",
            "color=black&operation=equal&cottonPart=100"
    })
    void getAmountOfSocks(String params) {
        ResponseEntity<Integer> responseEntity = restTemplate.exchange(
                "http://localhost:" + port + "/api/socks/?" + params,
                HttpMethod.GET,
                null,
                Integer.class);
        assertEquals(200, responseEntity.getStatusCodeValue());

    }

    @ParameterizedTest
    @ValueSource(strings = {"color=yellow&cottonPart=10&quantity=8",
            "color=white&cottonPart=60&quantity=88",
            "color=black&cottonPart=100&quantity=96"
    })
    void outcome(String params) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://localhost:" + port + "/api/socks/outcome?" + params,
                HttpMethod.POST,
                null,
                String.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

}