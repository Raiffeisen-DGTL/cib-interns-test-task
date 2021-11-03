package com.task.raif;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.task.raif.controller.SocksController;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MockTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SocksController controller;

    @Test
    void testGoodGetRequests() throws Exception {
        List<String> colors = List.of("red", "white", "black");
        List<String> operations = List.of("equal", "lessThan", "moreThan");
        List<String> cottonParts = List.of("0", "50", "100");
        for (String color : colors) {
            for (String operation : operations) {
                for (String cottonPart : cottonParts) {
                    this.mockMvc.perform(get("/api/socks")
                                    .queryParam("color", color)
                                    .queryParam("operation", operation)
                                    .queryParam("cottonPart", cottonPart))
                            .andDo(print())
                            .andExpect(status().isOk())
                            .andExpect(content().string(CoreMatchers.containsString("Quantity")));
                }
            }
        }
    }


    @Test
    void testBadGetRequests() throws Exception {
        List<String> colors = List.of("", "qwe", "black");
        List<String> operations = List.of("", "abc", "moreThan");
        List<String> cottonParts = List.of("", "abc");
        for (String color : colors) {
            for (String operation : operations) {
                for (String cottonPart : cottonParts) {
                    this.mockMvc.perform(get("/api/socks")
                                    .queryParam("color", color)
                                    .queryParam("operation", operation)
                                    .queryParam("cottonPart", cottonPart))
                            .andDo(print())
                            .andExpect(status().isBadRequest())
                            .andExpect(content().string(CoreMatchers.containsString("error")));
                }
            }
        }
    }

    @Test
    void testIncome() throws Exception {
        List<String> colors = List.of("abc", "qwe", "black");
        List<String> quantities = List.of("2", "0", "5");
        List<String> cottonParts = List.of("10", "20");
        for (String cottonPart : cottonParts) {
            for (String quantity : quantities) {
                for (String color : colors) {
                    Income income = new Income(color, cottonPart, quantity);
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
                    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
                    String requestJson=ow.writeValueAsString(income);
                    mockMvc.perform(post("/api/socks/income").contentType(APPLICATION_JSON)
                                    .content(requestJson))
                            .andExpect(status().isOk());
                }
            }
        }
    }

    @Test
    void testBadIncome() throws Exception {
        List<String> colors = List.of("qwe", "b");
        List<String> quantities = List.of("abc", "1231");
        List<String> cottonParts = List.of("abc", "321");
        for (String cottonPart : cottonParts) {
            for (String quantity : quantities) {
                for (String color : colors) {
                    Income income = new Income(color, cottonPart, quantity);
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
                    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
                    String requestJson=ow.writeValueAsString(income);
                    mockMvc.perform(post("/api/socks/income").contentType(APPLICATION_JSON)
                                    .content(requestJson))
                            .andExpect(status().isBadRequest());
                }
            }
        }
    }
}
