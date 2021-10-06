package org.vetirdoit.sock.registration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SockRegistrationApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    final String color = "blue";

    long countRequiredSocksByGetRequest(String operation, String cottonPartValue) throws Exception {

        String longStr = mockMvc.perform(
                get("/api/socks")
                        .param("color", color)
                        .param("operation", operation)
                        .param("cottonPart", cottonPartValue)
                ).andReturn()
                .getResponse()
                .getContentAsString();
        return Long.parseLong(longStr);
    }

    void registerSocksByPostRequest(String uri, Map<String, String> socks, ResultMatcher matcher) throws Exception {
        mockMvc.perform(
                post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(socks))
        ).andExpect(matcher);
    }

    @Test
    void doTests() throws Exception {
        assertThat(countRequiredSocksByGetRequest("greaterThan", "0"))
                .isEqualTo(0);
        var sock1 = Map.of(
                "color", color,
                "cottonPart", "0",
                "quantity", "10"
        );
        var sock2 = Map.of(
                "color", color,
                "cottonPart", "50",
                "quantity", "50"
        );
        var sock3 = Map.of(
                "color", color,
                "cottonPart", "100",
                "quantity", "100"
        );
        var invalidSock = Map.of(
                "color", color,
                "cottonPart", "100",
                "quantity", "0"
        );
        registerSocksByPostRequest("/api/socks/income", sock1, status().isOk());
        registerSocksByPostRequest("/api/socks/income", sock2, status().isOk());
        registerSocksByPostRequest("/api/socks/income", sock3, status().isOk());
        registerSocksByPostRequest("/api/socks/income", invalidSock, status().isBadRequest());

        assertThat(countRequiredSocksByGetRequest("greaterThan", "-1"))
                .isEqualTo(160);
        assertThat(countRequiredSocksByGetRequest("equal", "0"))
                .isEqualTo(10);
        assertThat(countRequiredSocksByGetRequest("lessThan", "100"))
                .isEqualTo(60);

        registerSocksByPostRequest("/api/socks/outcome", sock3, status().isOk());
        assertThat(countRequiredSocksByGetRequest("greaterThan", "-1"))
                .isEqualTo(60);

        registerSocksByPostRequest("/api/socks/outcome", sock3, status().isBadRequest());
        var tooManySock2 = Map.of(
                "color", color,
                "cottonPart", "50",
                "quantity", "100"
        );
        registerSocksByPostRequest("/api/socks/outcome", tooManySock2, status().isBadRequest());
        assertThat(countRequiredSocksByGetRequest("greaterThan", "-1"))
                .isEqualTo(60);
    }

}
