package ru.raiffeisen.socks.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import ru.raiffeisen.socks.service.SocksService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SocksControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SocksService socksServiceMock;

    private final String COMMON_PART = "/api/socks";
    private final int COTTON_PART = 25;
    private final long QUANTITY = 100;
    private final String COLOR = "red";
    private final String OPERATION = "lessThan";

    private final int INVALID_COTTON_PART = -30;
    private final long INVALID_QUANTITY = -10;
    private final String INVALID_OPERATION = "qwerty";


    @Test
    void income() throws Exception {
        incomeOutcomeTest("/income", status().isOk(), COLOR, COTTON_PART, QUANTITY);
        verify(socksServiceMock, times(1)).income(COLOR, COTTON_PART, QUANTITY);
    }

    @Test
    void incomeInvalid() throws Exception {
        incomeOutcomeTest("/income", status().isBadRequest(), COLOR, INVALID_COTTON_PART, QUANTITY);
        incomeOutcomeTest("/income", status().isBadRequest(), COLOR, COTTON_PART, INVALID_QUANTITY);

    }

    @Test
    void outcome() throws Exception {
        incomeOutcomeTest("/outcome", status().isOk(), COLOR, COTTON_PART, QUANTITY);
        verify(socksServiceMock, times(1)).outcome(COLOR, COTTON_PART, QUANTITY);
    }

    @Test
    void outcomeInvalid() throws Exception {
        incomeOutcomeTest("/outcome", status().isBadRequest(), COLOR, INVALID_COTTON_PART, QUANTITY);
        incomeOutcomeTest("/outcome", status().isBadRequest(), COLOR, COTTON_PART, INVALID_QUANTITY);
    }

    @Test
    void socks() throws Exception {
        socksTest(status().isOk(), COLOR, OPERATION, COTTON_PART);
        verify(socksServiceMock, times(1)).socks(COLOR, OPERATION, COTTON_PART);
    }

    @Test
    void socksInvalid() throws Exception {
        socksTest(status().isBadRequest(), COLOR, INVALID_OPERATION, COTTON_PART);
        socksTest(status().isBadRequest(), COLOR, OPERATION, INVALID_COTTON_PART);
    }


    private void incomeOutcomeTest(String api, ResultMatcher resultMatcher, String color, int cottonPart, long quantity) throws Exception {
        mockMvc.perform(
                post(COMMON_PART + api)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(incomeOutcomeContent(color, cottonPart, quantity))
        ).andExpect(resultMatcher);
    }

    private void socksTest(ResultMatcher resultMatcher, String color, String operation, int cottonPart) throws Exception {
        mockMvc.perform(
                get(COMMON_PART)
                        .param("color", color)
                        .param("operation", operation)
                        .param("cottonPart", String.valueOf(cottonPart)))
                .andExpect(resultMatcher);
    }


    private String incomeOutcomeContent(String color, int cottonPart, long quantity) {
        return String.format("{\"color\": \"%s\", " +
                "\"cottonPart\": \"%d\"," +
                "\"quantity\": \"%d\"}", color, cottonPart, quantity);
    }
}