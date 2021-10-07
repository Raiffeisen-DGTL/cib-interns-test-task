package com.raiffeisen.task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raiffeisen.task.domain.Sock;
import com.raiffeisen.task.dto.SockDto;
import com.raiffeisen.task.exception.ValidationException;
import com.raiffeisen.task.service.SockService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SockService sockService;

    @Autowired
    private Controller controller;

    @SneakyThrows
    @Test
    void incomeSocksSuccessfulTest() {
        SockDto sock = new SockDto("red", 50, 2);
        mockMvc.perform(post("/api/socks/income")
                .content(objectMapper.writeValueAsString(sock))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void incomeSocksFailedTest() {
        SockDto sock = new SockDto("red", 101, 0);
        mockMvc.perform(post("/api/socks/income")
                .content(objectMapper.writeValueAsString(sock))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @SneakyThrows
    @Test
    void outcomeSocksSuccessfulTest() {
        SockDto sock = new SockDto("red", 50, 2);
        mockMvc.perform(post("/api/socks/outcome")
                .content(objectMapper.writeValueAsString(sock))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void outcomeSocksFailedTest() {
        SockDto sock = new SockDto("red", 101, -1);
        mockMvc.perform(post("/api/socks/outcome")
                .content(objectMapper.writeValueAsString(sock))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @SneakyThrows
    @Test
    void returnTotalNumberSocksSuccessful() {
        mockMvc.perform(get("/api/socks")
                .param("operation", "equal")
                .param("color", "red")
                .param("cottonPart", "50"))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void returnTotalNumberSocksFailedCottonPart() {
        mockMvc.perform(get("/api/socks")
                .param("operation", "equal")
                .param("color", "red")
                .param("cottonPart", "101"))
                .andExpect(status().isBadRequest());
    }

}