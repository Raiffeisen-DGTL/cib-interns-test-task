package ru.dnsk.accountingofsocks.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import ru.dnsk.accountingofsocks.model.PairOfSocks;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-pair_of_socks-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-pair_of_socks-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class PairOfSocksControllerTest {

    private static final String MAIN_PATH = "/api/socks";
    private static final PairOfSocks PAIR_OF_SOCKS_TEST = new PairOfSocks("white", 100, 10);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void incomeTest() throws Exception {
        makeMockPostRequest("/income", objectMapper.writeValueAsString(PAIR_OF_SOCKS_TEST))
                .andExpect(status().isOk());
    }

    @Test
    public void outcomeTest() throws Exception {
        makeMockPostRequest("/outcome", objectMapper.writeValueAsString(PAIR_OF_SOCKS_TEST))
                .andExpect(status().isOk());
    }

    @Test
    public void incomeIncorrectParametersTest() throws Exception {
        PairOfSocks pairOfSocksIncorrectTest = new PairOfSocks("white", -1, 0);

        makeMockPostRequest("/income", objectMapper.writeValueAsString(pairOfSocksIncorrectTest))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void outcomeMissingParametersTest() throws Exception {
        String pairOfSocksJsonString = "\"color\":,\"cottonPart\":,\"quantity\":";

        makeMockPostRequest("/outcome", pairOfSocksJsonString)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void pairOfSocksTest() throws Exception {
        makeMockGetRequest("white", "equal", "100")
                .andExpect(status().isOk())
                .andExpect(content().string("10"));
    }

    @Test
    public void pairOfSocksIncorrectParametersTest() throws Exception {
        makeMockGetRequest("black", "incorrect", "")
                .andExpect(status().isBadRequest());
    }

    private ResultActions makeMockGetRequest(String color, String operation, String cottonPart) throws Exception {
        return this.mockMvc.perform(get(MAIN_PATH)
                        .param("color", color)
                        .param("operation", operation)
                        .param("cottonPart", cottonPart))
                .andDo(print());
    }

    private ResultActions makeMockPostRequest(String pathSegment, String pairOfSocksJsonString) throws Exception {
        return this.mockMvc.perform(post(MAIN_PATH + pathSegment)
                        .content(pairOfSocksJsonString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }
}
