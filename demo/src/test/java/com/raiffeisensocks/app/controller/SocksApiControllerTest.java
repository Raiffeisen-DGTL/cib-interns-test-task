package com.raiffeisensocks.app.controller;

import com.raiffeisensocks.app.dto.SocksDto;
import com.raiffeisensocks.app.repository.SocksRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SocksApiController.class)
class SocksApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SocksApiController socksApiController;

    @MockBean
    private SocksRepository repository;

    SocksDto socksDto1 = new SocksDto("red", 15, 5);
    SocksDto socksDto2 = new SocksDto("", 15, 5);
    SocksDto socksDto3 = new SocksDto("green", 50, 1);
    SocksDto socksDto4 = new SocksDto("blue", 110, 2);
    SocksDto socksDto5 = new SocksDto("orange", 50, -10);
    SocksDto socksDto6 = new SocksDto("yellow", 100, 0);

    SocksDto socksDto7 = new SocksDto("yellow", 10, -100);
    SocksDto socksDto8 = new SocksDto("red", 110, 1);
    SocksDto socksDto9 = new SocksDto("blue", -10, 50);
    SocksDto socksDto10 = new SocksDto("green", 0, 0);
    SocksDto socksDto11 = new SocksDto("", 100, 2);
    SocksDto socksDto12 = new SocksDto("yellow", 50, 3);


//    @Test
//    void testIncome() throws Exception {
//        addIncome(socksDto1, HttpStatus.OK);
//        addIncome(socksDto2, HttpStatus.BAD_REQUEST);
//        addIncome(socksDto3, HttpStatus.OK);
//        addIncome(socksDto4, HttpStatus.BAD_REQUEST);
//        addIncome(socksDto5, HttpStatus.BAD_REQUEST);
//        addIncome(socksDto6, HttpStatus.BAD_REQUEST);
//    }
//
//    private void addIncome(SocksDto socksDto, HttpStatus expected) throws Exception {
//        RequestBuilder request = MockMvcRequestBuilders.post("/api/socks/income")
//                                                .content(String.valueOf(socksDto))
//                                                .contentType(MediaType.APPLICATION_JSON)
//                                                .accept(MediaType.APPLICATION_JSON);
//
//        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
//
//        assertEquals(String.valueOf(expected), String.valueOf(result));
//    }
//
//    @Test
//    void testOutcome() throws Exception {
//        addOutcome(socksDto7, HttpStatus.BAD_REQUEST);
//        addOutcome(socksDto8, HttpStatus.BAD_REQUEST);
//        addOutcome(socksDto9, HttpStatus.BAD_REQUEST);
//        addOutcome(socksDto10, HttpStatus.OK);
//        addOutcome(socksDto11, HttpStatus.BAD_REQUEST);
//        addOutcome(socksDto12, HttpStatus.OK);
//    }
//
//    private void addOutcome(SocksDto socksDto, HttpStatus expected) throws Exception {
//        RequestBuilder request = MockMvcRequestBuilders.post("/api/socks/outcome")
//                .content(String.valueOf(socksDto))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON);
//
//        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
//
//        assertEquals(String.valueOf(expected), String.valueOf(result));
//    }
}