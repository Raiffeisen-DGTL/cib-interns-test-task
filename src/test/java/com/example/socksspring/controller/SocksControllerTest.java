package com.example.socksspring.controller;

import com.example.socksspring.Compare;
import com.example.socksspring.Socks;
import com.example.socksspring.repositories.SocksRepository;
import com.example.socksspring.service.SocksService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.Operation;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.mockito.BDDMockito.*;
import static org.hamcrest.CoreMatchers.is;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = SocksController.class)
@AutoConfigureMockMvc
public class SocksControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SocksRepository socksRepository;

    @MockBean
    private SocksService service;

    @Captor
    private ArgumentCaptor<Socks> socksCaptor;

    @Operation(summary = "При правильно выполненном запросе возвращается строка с номеров носков, " +
            "в тесте проверяется соответствие результата строке'")
    @Test
    public void addSocksValid() throws Exception {
        given(service.getAmountOfSocks("red", Compare.LessThan, 100)).willReturn(5);

        mvc.perform(get("/api/socks?color=red&operation=lessThan&cottonPart=100"))
                .andExpect(status().isOk())
                .andExpect(content().string("5"));
    }

    @Operation(summary = "При ошибке в формате параметров возвращается код 400 в виде json, в котором" +
            " проверяется ошибка 'Bad Request'")
    @Test
    public void addSocksInvalid() throws Exception {
        mvc.perform(get("/api/socks?color=red&operation=bad_enum&cottonPart=100"))
                .andExpect(status().isBadRequest());

        mvc.perform(get("/api/socks?color=red&operation=lessThan&cottonPart=1001"))
                .andExpect(status().isBadRequest());

        mvc.perform(get("/api/socks?color=red&operation=lessThan&cottonPart=-1001"))
                .andExpect(status().isBadRequest());
    }


    @Operation(summary = "Проверка на отпуск носков")
    @Test
    public void removeSocksValid() throws Exception {
        Mockito.when(socksRepository.getSocksEquals("red", 50))
                .thenReturn(Arrays.asList(new Socks(null, "red", 50, 5)));

        service.removeSocks(new Socks(null, "red", 50, 4));

        Mockito.verify(socksRepository, times(1)).updateRemoveSocks(socksCaptor.capture());
    }

}
