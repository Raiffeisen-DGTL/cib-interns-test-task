package org.vetirdoit.sock.registration.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers = SocksRegistrationController.class)
class SocksRegistrationControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @Test
    void registerIncomingSocks() {
    }

    @Test
    void registerOutgoingSocks() {
    }

    @Test
    void getAllRequiredSocks() {
    }
}