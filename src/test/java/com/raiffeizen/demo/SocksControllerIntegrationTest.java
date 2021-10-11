package com.raiffeizen.demo;

import com.raiffeizen.demo.controllers.SocksController;
import com.raiffeizen.demo.dao.SocksDao;
import com.raiffeizen.demo.services.SocksService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
public class SocksControllerIntegrationTest {

    @MockBean
    private SocksDao socksDao;

    @MockBean
    private SocksService socksService;

    @Autowired
    SocksController socksController;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void whenPostRequestToSocksIncomeAndValidSocks_thenCorrectResponse() throws Exception {
        String socks = "{\"color\": \"black\", \"cottonPart\" : \"50\", \"quantity\" : \"100\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/income")
                        .content(socks)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenPostRequestToSocksIncomeAndInValidSocksColor_thenCorrectResponse() throws Exception {
        MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, StandardCharsets.UTF_8);
        String socks = "{\"color\": \"\", \"cottonPart\" : \"50\", \"quantity\" : \"100\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/income")
                        .content(socks)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content()
                .contentType(textPlainUtf8))
                .andExpect(MockMvcResultMatchers.content()
                        .string("Параметры запроса отсутствуют или имеют некорректный формат"));
    }

    @Test
    public void whenPostRequestToSocksIncomeAndInValidSocksCottonPart_thenCorrectResponse() throws Exception {
        MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, StandardCharsets.UTF_8);
        String socks = "{\"color\": \"black\", \"cottonPart\" : \"-50\", \"quantity\" : \"100\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/income")
                        .content(socks)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(textPlainUtf8))
                .andExpect(MockMvcResultMatchers.content()
                        .string("Параметры запроса отсутствуют или имеют некорректный формат"));
    }

    @Test
    public void whenPostRequestToSocksIncomeAndInValidSocksQuantity_thenCorrectResponse() throws Exception {
        MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, StandardCharsets.UTF_8);
        String socks = "{\"color\": \"black\", \"cottonPart\" : \"50\", \"quantity\" : \"-50\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/income")
                        .content(socks)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(textPlainUtf8))
                .andExpect(MockMvcResultMatchers.content()
                        .string("Параметры запроса отсутствуют или имеют некорректный формат"));
    }

    @Test
    public void whenPostRequestToSocksOutcomeAndValidSocks_thenCorrectResponse() throws Exception {
        String socks = "{\"color\": \"black\", \"cottonPart\" : \"50\", \"quantity\" : \"10\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/outcome")
                        .content(socks)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenPostRequestToSocksOutcomeAndInValidSocksColor_thenCorrectResponse() throws Exception {
        MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, StandardCharsets.UTF_8);
        String socks = "{\"color\": \"\", \"cottonPart\" : \"50\", \"quantity\" : \"100\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/outcome")
                        .content(socks)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(textPlainUtf8))
                .andExpect(MockMvcResultMatchers.content()
                        .string("Параметры запроса отсутствуют или имеют некорректный формат"));
    }

    @Test
    public void whenPostRequestToSocksOutcomeAndInValidSocksCottonPart_thenCorrectResponse() throws Exception {
        MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, StandardCharsets.UTF_8);
        String socks = "{\"color\": \"black\", \"cottonPart\" : \"-50\", \"quantity\" : \"100\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/outcome")
                        .content(socks)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(textPlainUtf8))
                .andExpect(MockMvcResultMatchers.content()
                        .string("Параметры запроса отсутствуют или имеют некорректный формат"));
    }

    @Test
    public void whenPostRequestToSocksOutcomeAndInValidSocksQuantity_thenCorrectResponse() throws Exception {
        MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, StandardCharsets.UTF_8);
        String socks = "{\"color\": \"black\", \"cottonPart\" : \"50\", \"quantity\" : \"-50\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/outcome")
                        .content(socks)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(textPlainUtf8))
                .andExpect(MockMvcResultMatchers.content()
                        .string("Параметры запроса отсутствуют или имеют некорректный формат"));
    }
}
