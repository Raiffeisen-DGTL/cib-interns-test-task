package org.vetirdoit.sock.registration.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.vetirdoit.sock.registration.services.SockRegistrationService;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SocksRegistrationController.class)
class SocksRegistrationControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @MockBean private SockRegistrationService sockRegistrationService;

    void testRequestBodyOfIncomingSocks(Map<String, String> requestBody, ResultMatcher matcher) throws Exception {
        mockMvc.perform(post("/api/socks/income")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)) )
                .andExpect(matcher);
    }

    @Test
    void whenRequestBodyOfIncomeIsInvalidOrValid_thenReturnBadRequestOrOK() throws Exception {
        var validRequestBody = Map.of(
                "color", "red",
                "cottonPart", "50",
                "quantity", "1"
        );
        testRequestBodyOfIncomingSocks(validRequestBody, status().isOk());
        var totallyInvalidRequestBody = Map.of(
                "color", "RED",
                "cottonPart", "1f",
                "quantity", "1L"
        );

        for (Map.Entry<String, String> invalidPair : totallyInvalidRequestBody.entrySet()) {
            Map<String, String> invalidRequestBody = new HashMap<>(validRequestBody);
            invalidRequestBody.put(invalidPair.getKey(), invalidPair.getValue());
            testRequestBodyOfIncomingSocks(invalidRequestBody, status().isBadRequest());
        }
    }
}