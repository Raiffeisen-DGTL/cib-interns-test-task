package ru.testJava;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.testJava.controller.SocksController;
import ru.testJava.dto.SocksDto;
import ru.testJava.service.SocksService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SocksController.class)
public class SocksControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    SocksService serviceMock;

    @Test
    void incomeSocksTest() throws Exception {
        SocksDto socksDto = new SocksDto("black", 100, 10);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/income")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(socksDto)))
                .andExpect(status().isOk());
    }

    @Test
    void outcomeSocksTest() throws Exception{
        SocksDto socksDto = new SocksDto("red", 50, 100);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/socks/outcome")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(socksDto)))
                .andExpect(status().isOk());
    }

    @Test
    void allSocksTest() throws Exception {
        socksTest(status().isOk());
        verify(serviceMock, times(1)).getAll("red", "equals", 100);
    }

    private void socksTest(ResultMatcher resultMatcher) throws Exception {
        mockMvc.perform(
                        get("/api/socks")
                                .param("color", "red")
                                .param("operation", "equals")
                                .param("cottonPart", String.valueOf(100)))
                .andExpect(resultMatcher);
    }

}
