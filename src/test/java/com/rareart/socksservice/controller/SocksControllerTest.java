package com.rareart.socksservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rareart.socksservice.dto.SocksDto;
import com.rareart.socksservice.dto.request.SocksParamsRequest;
import com.rareart.socksservice.exceptions.InvalidRequestParamsException;
import com.rareart.socksservice.exceptions.NotEnoughItemsException;
import com.rareart.socksservice.exceptions.SocksNotFoundException;
import com.rareart.socksservice.service.SocksService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.rareart.socksservice.Api.BASE_ROUTE;
import static com.rareart.socksservice.Api.SERVICE_ROUTE;
import static com.rareart.socksservice.Api.Service.INCOME_ROUTE;
import static com.rareart.socksservice.Api.Service.OUTCOME_ROUTE;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SocksController.class)
public class SocksControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SocksService socksService;

    @Test
    void incomeSocksTest() throws Exception {
        SocksDto sockDto = new SocksDto("Black", new Byte("50"), 10);
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_ROUTE + SERVICE_ROUTE + INCOME_ROUTE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sockDto)))
                .andExpect(status().isOk());
    }

    @Test
    void outcomeSocksTest() throws Exception {
        SocksDto sockDto = new SocksDto("Green", new Byte("80"), 50);
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_ROUTE + SERVICE_ROUTE + OUTCOME_ROUTE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sockDto)))
                .andExpect(status().isOk());
    }

    @Test
    void getSocksCountByParamsTest() throws Exception {
        Mockito.when(socksService.getSocksCountByParams(
                new SocksParamsRequest("Blue", "moreThan", new Byte("50"))))
                .thenReturn(100L);
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_ROUTE + SERVICE_ROUTE)
                        .param("color", "Blue")
                        .param("operation", "moreThan")
                        .param("cottonPart", new Byte("50").toString()))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(100L)));
    }

    @Test
    void getSocksCountInvalidParamsTest() throws Exception {
        Mockito.when(socksService.getSocksCountByParams(
                        new SocksParamsRequest("Purple", "unknown", new Byte("-50"))))
                .thenThrow(InvalidRequestParamsException.class);
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_ROUTE + SERVICE_ROUTE)
                        .param("color", "Purple")
                        .param("operation", "unknown")
                        .param("cottonPart", new Byte("-50").toString()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void notEnoughItemsExceptionExceptionTest() throws Exception {
        SocksDto sockDto = new SocksDto("White", new Byte("85"), 1000);
        Mockito.doThrow(NotEnoughItemsException.class).when(socksService).outcomeSocks(sockDto);
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_ROUTE + SERVICE_ROUTE + OUTCOME_ROUTE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sockDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void socksNotFoundExceptionTest() throws Exception {
        SocksDto sockDto = new SocksDto("GenericColor", new Byte("30"), 500);
        Mockito.doThrow(SocksNotFoundException.class).when(socksService).outcomeSocks(sockDto);
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_ROUTE + SERVICE_ROUTE + OUTCOME_ROUTE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sockDto)))
                .andExpect(status().isBadRequest());
    }
}
