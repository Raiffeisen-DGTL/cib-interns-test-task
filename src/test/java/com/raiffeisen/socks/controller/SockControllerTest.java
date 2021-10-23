package com.raiffeisen.socks.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raiffeisen.socks.dto.SockDto;
import com.raiffeisen.socks.exceptions.IncorrectSockFormatException;
import com.raiffeisen.socks.exceptions.NotFoundSockException;
import com.raiffeisen.socks.service.SocksService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SocksController.class)
public class SockControllerTest {
    private static final String INCORRECT_OPERATION = "FAKE";
    private static final String CORRECT_OPERATION = "lessThan";
    private static final Integer CORRECT_COTTON_PART = 50;
    private static final Integer INCORRECT_COTTON_PART = -50;

    @MockBean
    private SocksService socksService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void incomeSocksTest() throws Exception {
        SockDto sockDto = new SockDto("red", 32, 32);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/income")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sockDto)))
                .andExpect(status().isOk());
    }

    @Test
    void outcomeSocksTest() throws Exception {
        SockDto sockDto = new SockDto("red", 32, 32);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/outcome")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sockDto)))
                .andExpect(status().isOk());
    }

    @Test
    void getSocksByParamsTest() throws Exception {
        SockDto sockDto = new SockDto("red", 32, 320);
        Mockito.when(socksService.getSocksByParams("red", CORRECT_OPERATION, CORRECT_COTTON_PART)).thenReturn(sockDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/socks")
                .param("color", "red")
                .param("operation", CORRECT_OPERATION)
                .param("cottonPart", CORRECT_COTTON_PART.toString()))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(sockDto)));
    }

    @Test
    void getSocksByIncorrectParamsTest() throws Exception {
        Mockito.when(socksService.getSocksByParams("red", INCORRECT_OPERATION, INCORRECT_COTTON_PART))
                .thenThrow(IncorrectSockFormatException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/socks")
                .param("color", "red")
                .param("operation", INCORRECT_OPERATION)
                .param("cottonPart", INCORRECT_COTTON_PART.toString()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void notFoundSockExceptionTest() throws Exception {
        SockDto sockDto = new SockDto("red", 32, 32);
        Mockito.doThrow(NotFoundSockException.class).when(socksService).registerSocks(sockDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/income")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sockDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void IncorrectSockExceptionTest() throws Exception {
        SockDto sockDto = new SockDto("red", 32, -32);
        Mockito.doThrow(NotFoundSockException.class).when(socksService).registerSocks(sockDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/income")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sockDto)))
                .andExpect(status().isBadRequest());
    }
}
