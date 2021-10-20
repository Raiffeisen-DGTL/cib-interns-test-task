package com.viktor.cibinternstesttask.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.viktor.cibinternstesttask.dto.SockDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class SockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    /*
     * ------------------------------ TEST POST /api/socks/income --------------------------------------------
     * */


    @Test
    public void incomeSocksTestAllValidParamsSuccess() throws Exception {
        SockDto sockDto = new SockDto("red", 50, 2L);
        mockMvc.perform(post("/api/socks/income")
                        .content(objectMapper.writeValueAsString(sockDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void incomeSocksTestColorIsEmptyFail() throws Exception {
        SockDto sockDto = new SockDto("", 50, 2L);
        mockMvc.perform(post("/api/socks/income")
                        .content(objectMapper.writeValueAsString(sockDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void incomeSocksTestColorIsNullFail() throws Exception {
        SockDto sockDto = new SockDto(null, 50, 2L);
        mockMvc.perform(post("/api/socks/income")
                        .content(objectMapper.writeValueAsString(sockDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void incomeSocksTestCottonPartEqualZeroSuccess() throws Exception {
        SockDto sockDto = new SockDto("red", 0, 2L);
        mockMvc.perform(post("/api/socks/income")
                        .content(objectMapper.writeValueAsString(sockDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void incomeSocksTestCottonPartLessThenZeroFail() throws Exception {
        SockDto sockDto = new SockDto("red", -1, 2L);
        mockMvc.perform(post("/api/socks/income")
                        .content(objectMapper.writeValueAsString(sockDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void incomeSocksTestCottonPartEqualHundredSuccess() throws Exception {
        SockDto sockDto = new SockDto("red", 100, 2L);
        mockMvc.perform(post("/api/socks/income")
                        .content(objectMapper.writeValueAsString(sockDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void incomeSocksTestCottonPartMoreThanHundredFail() throws Exception {
        SockDto sockDto = new SockDto("red", 101, 2L);
        mockMvc.perform(post("/api/socks/income")
                        .content(objectMapper.writeValueAsString(sockDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void incomeSocksTestCottonPartNullFail() throws Exception {
        SockDto sockDto = new SockDto("red", null, 2L);
        mockMvc.perform(post("/api/socks/income")
                        .content(objectMapper.writeValueAsString(sockDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void incomeSocksTestQuantityEqualZeroSuccess() throws Exception {
        SockDto sockDto = new SockDto("red", 20, 0L);
        mockMvc.perform(post("/api/socks/income")
                        .content(objectMapper.writeValueAsString(sockDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void incomeSocksTestQuantityLessThanZeroFail() throws Exception {
        SockDto sockDto = new SockDto("red", 20, -1L);
        mockMvc.perform(post("/api/socks/income")
                        .content(objectMapper.writeValueAsString(sockDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void incomeSocksTestQuantityNullFail() throws Exception {
        SockDto sockDto = new SockDto("red", 20, null);
        mockMvc.perform(post("/api/socks/income")
                        .content(objectMapper.writeValueAsString(sockDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    /*
     * ------------------------------ TEST POST /api/socks/outcome --------------------------------------------
     * */


    @Test
    public void outcomeSocksTestAllValidParamsSuccess() throws Exception {
        SockDto sockDto = new SockDto("red", 50, 2L);
        mockMvc.perform(post("/api/socks/outcome")
                        .content(objectMapper.writeValueAsString(sockDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void outcomeSocksTestColorIsEmptyFail() throws Exception {
        SockDto sockDto = new SockDto("", 50, 2L);
        mockMvc.perform(post("/api/socks/outcome")
                        .content(objectMapper.writeValueAsString(sockDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void otcomeSocksTestColorIsNullFail() throws Exception {
        SockDto sockDto = new SockDto(null, 50, 2L);
        mockMvc.perform(post("/api/socks/outcome")
                        .content(objectMapper.writeValueAsString(sockDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void outcomeSocksTestCottonPartEqualZeroSuccess() throws Exception {
        SockDto sockDto = new SockDto("red", 0, 2L);
        mockMvc.perform(post("/api/socks/outcome")
                        .content(objectMapper.writeValueAsString(sockDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void outcomeSocksTestCottonPartLessThenZeroFail() throws Exception {
        SockDto sockDto = new SockDto("red", -1, 2L);
        mockMvc.perform(post("/api/socks/outcome")
                        .content(objectMapper.writeValueAsString(sockDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void outcomeSocksTestCottonPartEqualHundredSuccess() throws Exception {
        SockDto sockDto = new SockDto("red", 100, 2L);
        mockMvc.perform(post("/api/socks/outcome")
                        .content(objectMapper.writeValueAsString(sockDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void outcomeSocksTestCottonPartMoreThanHundredFail() throws Exception {
        SockDto sockDto = new SockDto("red", 101, 2L);
        mockMvc.perform(post("/api/socks/outcome")
                        .content(objectMapper.writeValueAsString(sockDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void outcomeSocksTestCottonPartNullFail() throws Exception {
        SockDto sockDto = new SockDto("red", null, 2L);
        mockMvc.perform(post("/api/socks/outcome")
                        .content(objectMapper.writeValueAsString(sockDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void outcomeSocksTestQuantityEqualZeroSuccess() throws Exception {
        SockDto sockDto = new SockDto("red", 20, 0L);
        mockMvc.perform(post("/api/socks/outcome")
                        .content(objectMapper.writeValueAsString(sockDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void outcomeSocksTestQuantityLessThanZeroFail() throws Exception {
        SockDto sockDto = new SockDto("red", 20, -1L);
        mockMvc.perform(post("/api/socks/outcome")
                        .content(objectMapper.writeValueAsString(sockDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void outcomeSocksTestQuantityNullFail() throws Exception {
        SockDto sockDto = new SockDto("red", 20, null);
        mockMvc.perform(post("/api/socks/outcome")
                        .content(objectMapper.writeValueAsString(sockDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    /*
     * ------------------------------ TEST GET /api/socks --------------------------------------------
     * */


    @Test
    public void getSocksTestAllValidParamsSuccess() throws Exception {
        mockMvc.perform(get("/api/socks")
                        .param("color", "red")
                        .param("cottonPart", "10")
                        .param("operation", "equal"))
                .andExpect(status().isOk());
    }

    @Test
    public void getSocksTestEmptyColorFail() throws Exception {
        mockMvc.perform(get("/api/socks")
                        .param("color", "")
                        .param("cottonPart", "10")
                        .param("operation", "equal"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getSocksTestNullColorFail() throws Exception {
        mockMvc.perform(get("/api/socks")
                        .param("color", (String) null)
                        .param("cottonPart", "10")
                        .param("operation", "equal"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getSocksTestCottonPartEqualZeroSuccess() throws Exception {
        mockMvc.perform(get("/api/socks")
                        .param("color", "red")
                        .param("cottonPart", "0")
                        .param("operation", "equal"))
                .andExpect(status().isOk());
    }

    @Test
    public void getSocksTestCottonPartLessThanZeroFail() throws Exception {
        mockMvc.perform(get("/api/socks")
                        .param("color", "red")
                        .param("cottonPart", "-1")
                        .param("operation", "equal"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getSocksTestCottonPartEqualHundredSuccess() throws Exception {
        mockMvc.perform(get("/api/socks")
                        .param("color", "red")
                        .param("cottonPart", "100")
                        .param("operation", "equal"))
                .andExpect(status().isOk());
    }

    @Test
    public void getSocksTestCottonPartMoreThanHundredFail() throws Exception {
        mockMvc.perform(get("/api/socks")
                        .param("color", "red")
                        .param("cottonPart", "101")
                        .param("operation", "equal"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getSocksTestCottonPartNullFail() throws Exception {
        mockMvc.perform(get("/api/socks")
                        .param("color", "red")
                        .param("cottonPart", (String) null)
                        .param("operation", "equal"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getSocksTestEqualOperationSuccess() throws Exception {
        mockMvc.perform(get("/api/socks")
                        .param("color", "red")
                        .param("cottonPart", "20")
                        .param("operation", "equal"))
                .andExpect(status().isOk());
    }

    @Test
    public void getSocksTestMoreThanOperationSuccess() throws Exception {
        mockMvc.perform(get("/api/socks")
                        .param("color", "red")
                        .param("cottonPart", "20")
                        .param("operation", "moreThan"))
                .andExpect(status().isOk());
    }

    @Test
    public void getSocksTestLessThanOperationSuccess() throws Exception {
        mockMvc.perform(get("/api/socks")
                        .param("color", "red")
                        .param("cottonPart", "20")
                        .param("operation", "lessThan"))
                .andExpect(status().isOk());
    }

    @Test
    public void getSocksTestNullOperationFail() throws Exception {
        mockMvc.perform(get("/api/socks")
                        .param("color", "red")
                        .param("cottonPart", "20")
                        .param("operation", (String) null))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getSocksTestWrongOperationFail() throws Exception {
        mockMvc.perform(get("/api/socks")
                        .param("color", "red")
                        .param("cottonPart", "20")
                        .param("operation", "like"))
                .andExpect(status().isBadRequest());
    }

}
