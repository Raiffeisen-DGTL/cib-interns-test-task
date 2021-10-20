package com.example.cibinternstesttask;

import com.example.cibinternstesttask.ui.controllers.SockController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CibInternsTestTaskApplicationTests {

    @Autowired
    SockController sockController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void validIncomeRequestTest() throws Exception {
        String income = "{\"color\": \"red\", \"cottonPart\" : \"30\", \"quantity\" : \"2\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/income")
                        .content(income)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void invalidIncomeRequestTest() throws Exception {
        String income1 = "{\"color\": \"black\", \"cottonPart\" : \"150\", \"quantity\" : \"2\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/income")
                        .content(income1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        String income2 = "{\"color\": \"black\", \"cottonPart\" : \"-5\", \"quantity\" : \"2\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/income")
                        .content(income2)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        String income3 = "{\"color\": \"black\", \"cottonPart\" : \"56\", \"quantity\" : \"0\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/income")
                        .content(income3)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        String income4 = "{\"color\": \"black\", \"cottonPart\" : \"56\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/income")
                        .content(income4)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        String income5 = "{\"color\": \"\", \"cottonPart\" : \"56\", \"quantity\" : \"3\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/income")
                        .content(income5)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void validOutcomeRequestTest() throws Exception {
        String income = "{\"color\": \"green\", \"cottonPart\" : \"45\", \"quantity\" : \"3\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/income")
                        .content(income)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        String outcome = "{\"color\": \"green\", \"cottonPart\" : \"45\", \"quantity\" : \"3\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/outcome")
                        .content(outcome)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void invalidOutcomeRequestTest() throws Exception {
        String income = "{\"color\": \"white\", \"cottonPart\" : \"30\", \"quantity\" : \"3\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/income")
                        .content(income)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        String outcome = "{\"color\": \"white\", \"cottonPart\" : \"30\", \"quantity\" : \"4\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/outcome")
                        .content(outcome)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        String outcome2 = "{\"color\": \"white\", \"cottonPart\" : \"56\", \"quantity\" : \"4\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/outcome")
                        .content(outcome2)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void validGetSocksQuantityTest() throws Exception {
        String income = "{\"color\": \"purple\", \"cottonPart\" : \"55\", \"quantity\" : \"3\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/income")
                        .content(income)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        income = "{\"color\": \"red\", \"cottonPart\" : \"95\", \"quantity\" : \"3\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/income")
                        .content(income)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        income = "{\"color\": \"red\", \"cottonPart\" : \"96\", \"quantity\" : \"5\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/income")
                        .content(income)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/socks")
                        .param("color", "red")
                        .param("operation", "moreThan")
                        .param("cottonPart", "90"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
