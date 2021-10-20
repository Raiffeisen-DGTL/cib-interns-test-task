package com.herokuapp.cibinternship;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.herokuapp.cibinternship.model.Socks;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
public class WebLayerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnStatus200WhenIncomeIsValid() throws Exception {
        Socks socks = new Socks("red", 18, 10);
        this.mockMvc.perform(post("/api/socks/income")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(socks)))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnStatus400WhenIncomeIsInvalid() throws Exception {
        Socks socks = new Socks("red", 101, 10);
        this.mockMvc.perform(post("/api/socks/income")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(socks)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnStatus200WhenOutcomeIsValid() throws Exception {
        Socks socks = new Socks("red", 18, 5);
        this.mockMvc.perform(post("/api/socks/outcome")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(socks)))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnStatus400WhenSocksOutcomeNotFound() throws Exception {
        Socks socks = new Socks(null, 18, 5);
        this.mockMvc.perform(post("/api/socks/outcome")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(socks)))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void shouldReturnStatus200WhenGetRequestIsValid() throws Exception {
        this.mockMvc.perform(get("/api/socks")
                        .param("color", "red")
                        .param("operation", "moreThan")
                        .param("cottonPart", "15"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnStatus400WhenGetRequestIsInvalid() throws Exception {
        this.mockMvc.perform(get("/api/socks")
                        .param("not_a_color", "red")
                        .param("operation", "moreThan")
                        .param("cottonPart", "15"))
                .andExpect(status().isBadRequest());
    }
}
