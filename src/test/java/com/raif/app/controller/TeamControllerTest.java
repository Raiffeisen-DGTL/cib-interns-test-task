package com.raif.app.controller;

import com.raif.app.dao.model.Team;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("test")
@AutoConfigureEmbeddedDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TeamControllerTest {

    @Autowired
    public TestRestTemplate template;

    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Test
    void findAll() {

    }

    @Test
    void createTeam() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        Team team = new Team();
        team.setName("123");
        team.setTag("431213");
        team.setType("3213414");

        ResponseEntity<String> response = template.postForEntity("/rest/team/save",
                new HttpEntity<Team>(team, httpHeaders),
                String.class);

        System.out.println(response.getBody());
        System.out.println(response.getStatusCode());

        Map<String, Object> stringObjectMap = jdbcTemplate.queryForMap("Select * from team where name='123'");

        System.out.print(stringObjectMap);
        assertEquals(stringObjectMap.get("name").toString(), "123");

    }
}