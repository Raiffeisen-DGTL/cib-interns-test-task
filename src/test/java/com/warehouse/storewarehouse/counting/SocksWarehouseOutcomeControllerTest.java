package com.warehouse.storewarehouse.counting;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest
public class SocksWarehouseOutcomeControllerTest {

    @MockBean
    private SocksService socksService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DeliveryBatchSocks> jsonRequestAttempt;

    private final String urlTemplate = "/api/socks/outcome";

    @Test
    public void simpleTest() throws Exception {
        DeliveryBatchSocks delivery1 = new DeliveryBatchSocks("White", 25, 9999);

        mvc.perform(
                post("/api/socks/income").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestAttempt.write(delivery1).getJson())
        );

        DeliveryBatchSocks delivery = new DeliveryBatchSocks("White", 25, 1234);

        MockHttpServletResponse response = mvc.perform(
                post(urlTemplate).contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestAttempt.write(delivery).getJson())
        ).andReturn().getResponse();

        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
