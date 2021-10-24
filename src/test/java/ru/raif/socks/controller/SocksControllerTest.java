package ru.raif.socks.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.raif.socks.model.SocksModel;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class SocksControllerTest {

    private final MockMvc mvc;
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public SocksControllerTest(MockMvc mvc) {
        this.mvc = mvc;
    }

    @SneakyThrows
    private String toJson(Object o) {
        return mapper.writeValueAsString(o);
    }

    @Test
    @Order(1)
    @SneakyThrows
    void shouldAcceptNewSocksIncome() {
        mvc.perform(post("/api/socks/income")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(new SocksModel(1L,"yellow", 40, 5)))
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk());

        mvc.perform(get("/api/socks?color=yellow&operation=equal&cottonPart=40"))
                .andExpect(status().isOk())
                .andExpect(content().string("5"));
    }

    @Test
    @Order(2)
    @SneakyThrows
    void shouldAcceptSocksOutcome() {
        mvc.perform(post("/api/socks/outcome")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(new SocksModel(1L,"yellow", 40, 5)))
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk());

        mvc.perform(get("/api/socks?color=yellow&operation=equal&cottonPart=40"))
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
    }

    @Test
    @SneakyThrows
    void shouldReturnBadRequestWhenBadSockGiven() {
        mvc.perform(post("/api/socks/income")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(new SocksModel(1L,"yellow", 40, -5)))
                .characterEncoding("UTF-8"))
                .andExpect(status().is4xxClientError());

        mvc.perform(post("/api/socks/income")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(new SocksModel(1L,"yellow", 120, 1)))
                .characterEncoding("UTF-8"))
                .andExpect(status().is4xxClientError());

        mvc.perform(post("/api/socks/income")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(new SocksModel(1L,"yellow", -10, 1)))
                .characterEncoding("UTF-8"))
                .andExpect(status().is4xxClientError());
    }
}
