package com.lazarev.socksapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.lazarev.socksapi.dto.SockDto;
import com.lazarev.socksapi.exception.NotEnoughSocksOnStorehouseException;
import com.lazarev.socksapi.exception.OperationNotFoundException;
import com.lazarev.socksapi.exception.SockNotFoundException;
import com.lazarev.socksapi.service.SockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@WebMvcTest
class SockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SockService sockService;

    @Test
    public void canAddSocksToStoreHouse_success() throws Exception {
        SockDto dto = new SockDto("red", 40, 100);

        String url = "/api/socks/income";

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(dto);

        mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void canAddSocksToStoreHouse_fail_quantity_should_be_specified() throws Exception {
        SockDto dto = new SockDto()
                .setColor("red")
                .setCottonPart(40);

        String url = "/api/socks/income";

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(dto);

        mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void canAddSocksToStoreHouse_fail_quantity_should_be_positive() throws Exception {
        SockDto dto = new SockDto("red", 40, -1);

        String url = "/api/socks/income";

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(dto);

        mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void canAddSocksToStoreHouse_fail_cottonPart_should_be_in_range_from_0_to_100() throws Exception {
        SockDto dto = new SockDto("red", 101, 100);

        String url = "/api/socks/income";

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(dto);

        mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void canAddSocksToStoreHouse_fail_color_should_not_be_blank() throws Exception {
        SockDto dto = new SockDto("   ", 101, 100);

        String url = "/api/socks/income";

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(dto);

        mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void canAddSocksToStoreHouse_fail_save_throws_exception() throws Exception {
        SockDto dto = new SockDto("red", 80, 100);

        when(sockService.addSocks(dto))
                .thenThrow(new SockNotFoundException("sock not found"));

        String url = "/api/socks/income";

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(dto);

        mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void canSubSocksToStoreHouse_fail_save_throws_exception() throws Exception {
        SockDto dto = new SockDto("red", 80, 100);

        when(sockService.subSocks(dto))
                .thenThrow(new NotEnoughSocksOnStorehouseException("not enough socks"));

        String url = "/api/socks/outcome";

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(dto);

        mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void canSubSocksToStoreHouse_success() throws Exception {
        SockDto dto = new SockDto("red", 40, 100);

        String url = "/api/socks/outcome";

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(dto);

        mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void canGetCountRequestMatchingSocks_success() throws Exception {
        String color = "red";
        Integer cottonPart = 40;
        String operation = "moreThan";

        when(sockService.countRequestMatchingSocks(color, operation, cottonPart))
                .thenReturn(100);

        String url = "/api/socks?color=" + color + "&operation=" + operation + "&cottonPart=" + cottonPart;

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).isEqualTo(Integer.toString(100));
    }

    @Test
    public void canGetCountRequestMatchingSocks_fail_incorrect_operation() throws Exception {
        String color = "red";
        Integer cottonPart = 40;
        String operation = "moreThanEquals";

        when(sockService.countRequestMatchingSocks(color, operation, cottonPart))
                .thenThrow(new OperationNotFoundException("incorrect operation"));

        String url = "/api/socks?color=" + color + "&operation=" + operation + "&cottonPart=" + cottonPart;

        mockMvc.perform(
                MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void canGetCountRequestMatchingSocks_fail_params_not_specified() throws Exception {
        String url = "/api/socks";

        mockMvc.perform(
                MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}