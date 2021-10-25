package ru.raiffeisen.socks.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.raiffeisen.socks.dto.SocksRequest;
import ru.raiffeisen.socks.service.SockService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SockController.class)
class SockControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private SockService sockService;

    @Test
    void whenSocksInputIsInvalid_thenReturnsStatus400() throws Exception {
        mvc.perform(get("/api/socks?color=red&cottonPart=1000&operation=lessThan"))
                .andExpect(status().isBadRequest());

        mvc.perform(get("/api/socks?color=red&cottonPart=50&operation=less"))
                .andExpect(status().isBadRequest());

        mvc.perform(get("/api/socks?color=red&cottonPart=1000&operation=moreThan"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenIncomeInputIsInvalid_thenReturnsStatus400() throws Exception {
        SocksRequest request = new SocksRequest("red", 50, 0);

        mvc.perform(post("/api/socks/income")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenOutcomeInputIsInvalid_thenReturnsStatus400() throws Exception {
        SocksRequest request = new SocksRequest("red", 150, 5);

        mvc.perform(post("/api/socks/outcome")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}