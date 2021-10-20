package com.raif.app.controller;

import com.raif.app.controller.dto.SocksIncomeDTO;
import com.raif.app.dao.mapper.SockStorageMapper;
import com.raif.app.dao.model.SocksStorage;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@AutoConfigureEmbeddedDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SocksStorageControllerTest {

    @Autowired
    public TestRestTemplate template;

    @Autowired
    public NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public SockStorageMapper sockStorageMapper;

    @Test
    public void registerIncome() {
        String color = "red";
        int cottonPart = 15;
        long quantity = 1000L;
        SocksIncomeDTO dto = new SocksIncomeDTO(
                color, cottonPart, quantity
        );
        ResponseEntity<String> response =
                template.postForEntity("/api/socks/income",
                        new HttpEntity<>(dto),
                        String.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);

        SocksStorage socksStorage = jdbcTemplate.queryForObject(
                "SELECT * from socks_storage WHERE color = :color AND cotton_part = :cotton_part",
                new MapSqlParameterSource()
                        .addValue("color", dto.getColor())
                        .addValue("cotton_part", dto.getCottonPart()),
                sockStorageMapper
        );

        assertEquals(socksStorage.getColor(), color);
        assertEquals(socksStorage.getCottonPart(), cottonPart);
        assertEquals(socksStorage.getQuantity(), quantity);
    }

}