package com.herokuapp.cibinternship;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.herokuapp.cibinternship.model.Socks;
import com.herokuapp.cibinternship.service.SocksService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ServiceLayerTests {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    @Autowired
    private SocksService socksService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    public void shouldRegisterCorrectIncome() throws Exception {
        Socks socksIncome = new Socks("test_red", 42, 100);
        socksService.registerSocksIncome(socksIncome);

        this.mockMvc.perform(get("/api/socks")
                        .param("color", "test_red")
                        .param("operation", "equal")
                        .param("cottonPart", "42"))
                .andExpect(status().isOk()).andExpect(content().string(equalTo("100")));
    }

    @Test
    @Order(2)
    public void shouldRegisterCorrectOutcome() throws Exception {
        Socks socksOutcome = new Socks("test_red", 42, 50);
        socksService.registerSocksOutcome(socksOutcome);

        this.mockMvc.perform(get("/api/socks")
                        .param("color", "test_red")
                        .param("operation", "equal")
                        .param("cottonPart", "42"))
                .andExpect(status().isOk()).andExpect(content().string(equalTo("50")));
    }


    @Test
    @Order(3)
    public void shouldReturnStatus400WhenNotEnoughSocksInStock() throws Exception {
        Socks socks = new Socks("test_red", 42, 500);
        this.mockMvc.perform(post("/api/socks/outcome")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(socks)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnStatus400WhenSocksNotFound() throws Exception {
        Socks socks = new Socks("test_red", 24, 50);
        this.mockMvc.perform(post("/api/socks/outcome")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(socks)))
                .andExpect(status().isBadRequest());
    }
}
