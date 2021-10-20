package ru.samusev.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import ru.samusev.api.AbstractTest;
import ru.samusev.api.constant.Operation;
import ru.samusev.api.constant.SocksColor;
import ru.samusev.api.dto.SocksRequest;
import ru.samusev.api.dto.SocksResponse;
import ru.samusev.api.sercvice.SocksService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.samusev.api.constant.Paths.API;
import static ru.samusev.api.constant.Paths.INCOME;
import static ru.samusev.api.constant.Paths.OUTCOME;
import static ru.samusev.api.constant.Paths.SOCKS;

@Sql("classpath:sql/SocksServiceImpl.sql")
class SocksControllerTest extends AbstractTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SocksService socksService;

    @Autowired
    private ObjectMapper om;

    @Test
    void getAllByCriterion() throws Exception {
        String color = SocksColor.RED.name();
        Integer cottonPart = 50;
        String operation = Operation.MORE_THAN.getTitle();
        Long response = socksService.getQuantityByCriterion(color, cottonPart, operation);

        mockMvc.perform(get(API + SOCKS)
                    .param("color", color)
                    .param("cottonPart", cottonPart.toString())
                    .param("operation", operation))
                .andExpect(status().isOk())
                .andExpect(content().string(response.toString()));
    }

    @Test
    void outcome() throws Exception {
        SocksRequest request = new SocksRequest()
                .setColor(SocksColor.RED.name())
                .setQuantity(10L)
                .setCottonPart(50);
        SocksResponse response = new SocksResponse()
                .setColor(SocksColor.RED.name())
                .setQuantity(30L)
                .setCottonPart(50);

        mockMvc.perform(post(API + SOCKS + OUTCOME)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(om.writeValueAsString(response)));
    }

    @Test
    void outcome_withBadRequest() throws Exception {
        SocksRequest request = new SocksRequest();

        mockMvc.perform(post(API + SOCKS + OUTCOME)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void income() throws Exception {
        SocksRequest request = new SocksRequest()
                .setColor(SocksColor.GREEN.name())
                .setQuantity(110L)
                .setCottonPart(100);
        SocksResponse response = new SocksResponse()
                .setColor(SocksColor.GREEN.name())
                .setQuantity(110L)
                .setCottonPart(100);

        mockMvc.perform(post(API + SOCKS + INCOME)
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(request)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(om.writeValueAsString(response)));
    }

    @Test
    void income_withBadRequest() throws Exception {
        SocksRequest request = new SocksRequest();

        mockMvc.perform(post(API + SOCKS + INCOME)
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}
