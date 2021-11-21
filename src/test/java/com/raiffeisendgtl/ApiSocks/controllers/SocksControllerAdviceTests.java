package com.raiffeisendgtl.ApiSocks.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
public class SocksControllerAdviceTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void requestSocksError() throws Exception {
        String color = "green";
        String operation = "lessThan";
        int cottonPart = 15;

        mockMvc.perform(get("/api/socks?color={color}&operation={operation}&cottonPart={cottonPart}",
                color, operation, cottonPart)).andExpect(status().is4xxClientError());
    }

}
