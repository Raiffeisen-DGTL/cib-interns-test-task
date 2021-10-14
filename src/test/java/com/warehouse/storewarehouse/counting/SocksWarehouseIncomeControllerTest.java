package com.warehouse.storewarehouse.counting;


import org.junit.jupiter.api.BeforeEach;
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

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest
public class SocksWarehouseIncomeControllerTest {

    @MockBean
    private SocksService socksService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DeliveryBatchSocks> jsonRequestAttempt;

    private final String urlTemplate = "/api/socks/income";

    @Test
    public void postValidResult() throws Exception {
        //given
        DeliveryBatchSocks delivery = new DeliveryBatchSocks("Black", 40, 23);

        //when
        MockHttpServletResponse response = mvc.perform(
                post(urlTemplate).contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestAttempt.write(delivery).getJson())
        ).andReturn().getResponse();

        //then
        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

}
