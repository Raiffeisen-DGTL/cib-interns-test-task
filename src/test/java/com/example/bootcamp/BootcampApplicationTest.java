package com.example.bootcamp;

import com.example.bootcamp.entity.SocksEntity;
import com.example.bootcamp.repo.SocksRepo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles
@SpringBootTest
@AutoConfigureMockMvc
public class BootcampApplicationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    SocksRepo socksRepo;

    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    @Transactional
    void setup() {

        socksRepo.save(new SocksEntity("red", 60, 8));

        socksRepo.save(new SocksEntity("black", 90, 15));

    }

    @AfterEach
    @Transactional
    void cleanup() {
        socksRepo.deleteAll();
    }

    @Test
    void givenJson_andIncomeRequestSuccessfulIncome() throws Exception {
        String body = """
                [
                {"color": "red","cottonPart": 60,"quantity": "22"},
                {"color": "black","cottonPart": 90,"quantity": "4"}
                ]
                """;

        mockMvc.perform(
                        post("/api/socks/income")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(body)
                )
                .andExpect(status().isOk());
    }

    @Test
    void givenJson_andOutcomeRequestSuccessfulOutcome() throws Exception {
        String body = """
                [
                {"color": "red","cottonPart": 60,"quantity": "3"},
                {"color": "black","cottonPart": 90,"quantity": "2"}
                ]
                """;

        mockMvc.perform(
                        post("/api/socks/outcome")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(body)
                )
                .andExpect(status().isOk());
    }

    @Test
    void getSocksAndCheckJson() throws Exception {
        String resultRed = mockMvc.perform(get
                        ("/api/socks?color=red&operation=moreThan&cottonPart=30"))
                .andReturn().getResponse().getContentAsString();

        Integer countRed = mapper.readValue(resultRed, new TypeReference<>() {
        });


        String resultBlack = mockMvc.perform(get
                        ("/api/socks?color=red&operation=equal&cottonPart=90"))
                .andReturn().getResponse().getContentAsString();

        Integer countBlack = mapper.readValue(resultBlack, new TypeReference<>() {
        });

        int cnt = countRed + countBlack;

        assertEquals(23, cnt);
    }
}
