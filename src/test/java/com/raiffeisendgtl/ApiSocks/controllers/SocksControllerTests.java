package com.raiffeisendgtl.ApiSocks.controllers;

import com.google.gson.Gson;
import com.raiffeisendgtl.ApiSocks.entities.Socks;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
public class SocksControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Sql(value = {"/delete-socks-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void requestIncomePost() throws Exception {
        Socks request = new Socks("red", 33, 3);
        Gson gson = new Gson();
        String json = gson.toJson(request);

        mockMvc.perform(post("/api/socks/income")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());
    }

    @Test
    @Sql(value = {"/insert-socks-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/delete-socks-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void requestOutcomePost() throws Exception {
        Socks request = new Socks("black", 50, 4);
        Gson gson = new Gson();
        String json = gson.toJson(request);

        mockMvc.perform(post("/api/socks/outcome")
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());
    }

    @Test
    @Sql(value = {"/insert-socks-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/delete-socks-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void requestSocksGet() throws Exception {
        String color = "black";
        String operation = "moreThan";
        int cottonPart = 10;
        String expectedResult = "5";

        MvcResult mvcResult = mockMvc.perform(get("/api/socks?color={color}&operation={operation}&cottonPart={cottonPart}",
                        color, operation, cottonPart)).andExpect(status().isOk()).andReturn();
        String actualResult = mvcResult.getResponse().getContentAsString();

        assertEquals(expectedResult, actualResult);
    }

}
