package ru.raiffeisen.dgtl.cib.intern.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.raiffeisen.dgtl.cib.intern.task.entity.Socks;
import ru.raiffeisen.dgtl.cib.intern.task.service.SocksServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SocksController.class)
public class SocksControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SocksServiceImpl socksService;

    @Test
    void whenIncomeValidSocks_thenReturns200() throws Exception {
        Socks socks = new Socks("black", (byte) 50, 100L);
        mockMvc.perform(post("/api/socks/income")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(socks)))
                .andExpect(status().isOk());
    }

    @Test
    void whenIncomeCottonPartNull_thenReturns400() throws Exception {
        Socks socks = new Socks("black", (byte) -1, 100L);
        mockMvc.perform(post("/api/socks/income")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(socks)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenOutcomeValidSocks_thenReturns200() throws Exception {
        Socks socks = new Socks("black", (byte) 50, 100L);
        mockMvc.perform(post("/api/socks/outcome")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(socks)))
                .andExpect(status().isOk());
    }

    @Test
    void whenGetQuantity_thenReturns200() throws Exception {
        mockMvc.perform(get("/api/socks")
                .param("operation", String.valueOf(Operation.EQUAL))
                .param("color", "red")
                .param("cottonPart", "10"))
                .andExpect(status().isOk());
    }

    @Test
    void whenWrongOperation_thenReturns400() throws Exception {
        mockMvc.perform(get("/api/socks")
                .param("operation", "")
                .param("color", "red")
                .param("cottonPart", "10"))
                .andExpect(status().isBadRequest());
    }
}
