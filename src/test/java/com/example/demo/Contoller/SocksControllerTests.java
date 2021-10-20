package com.example.demo.Contoller;

import com.example.demo.model.Sock;
import com.example.demo.repository.SockRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SocksControllerTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private SockRepository repository;

    @Test
    public void getSocksWithParams() throws Exception {
        repository.deleteAll();
        this.repository.save(new Sock("red", 10, 50));
        this.repository.save(new Sock("red", 30, 100));
        this.repository.save(new Sock("red", 50, 200));
        mvc.perform(MockMvcRequestBuilders.get("/api/socks?color=red&operation=moreThan&cottonPart=5")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("350")));
        mvc.perform(MockMvcRequestBuilders.get("/api/socks?color=red&operation=lessThan&cottonPart=35")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("150")));
        mvc.perform(MockMvcRequestBuilders.get("/api/socks?color=red&operation=equal&cottonPart=30")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("100")));
    }

    @Test
    public void NewSocksIncome() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post(
                                "/api/socks/income?color=red&quantity=50&cottonPart=73")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        assertEquals(this.repository.findByColorAndCottonPart("red", 73).get(0).getCottonPart(), 73);
    }

    @Test
    public void SocksIncomeBadParamsFail() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post(
                                "/api/socks/income?color=red&quantity=50")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
        mvc.perform(MockMvcRequestBuilders.post(
                                "/api/socks/income?color=red&cottonPart=73")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
        mvc.perform(MockMvcRequestBuilders.post(
                                "/api/socks/income?quantity=50&cottonPart=73")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());


    }

    @Test
    public void SocksIncomeBadRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/socks/income?color=red&quantity=-5&cottonPart=90").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }

    @Test
    public void SocksOutcome() throws Exception {
        this.repository.deleteAll();
        this.repository.save(new Sock("green", 11, 103));
        mvc.perform(MockMvcRequestBuilders.post(
                                "/api/socks/outcome?color=green&quantity=50&cottonPart=11")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        assertEquals(this.repository.findByColorAndCottonPart("green", 11).get(0).getQuantity(), 53);
    }

    @Test
    public void SocksOutcomeBadParamsFail() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.post(
//                                "/api/socks/outcome?color=red&quantity=50&cottonPart=73")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().is4xxClientError());
        mvc.perform(MockMvcRequestBuilders.post(
                                "/api/socks/outcome?color=red&quantity=50")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
        mvc.perform(MockMvcRequestBuilders.post(
                                "/api/socks/outcome?color=red&cottonPart=73")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
        mvc.perform(MockMvcRequestBuilders.post(
                                "/api/socks/outcome?quantity=50&cottonPart=73")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());


    }

//    @Test
//    public void SocksOutcome() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.post("/api/socks/outcome").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
}