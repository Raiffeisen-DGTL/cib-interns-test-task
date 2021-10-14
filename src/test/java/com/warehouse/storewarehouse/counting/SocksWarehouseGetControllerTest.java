package com.warehouse.storewarehouse.counting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.ArgumentMatchers.eq;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest
public class SocksWarehouseGetControllerTest {

    @MockBean
    private SocksService socksService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DeliveryBatchSocks> jsonRequestSerializer;

    @Autowired
    private JacksonTester<SocksInfo> jsonResponseSerializer;

    private final String urlTemplate = "/api/socks";
    private final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

    @Test
    public void simpleGetTest() throws Exception {

        DeliveryBatchSocks batch = new DeliveryBatchSocks("Green", 50, 10);
        SocksInfo expectedResult = new SocksInfo("10");

        mvc.perform(post(urlTemplate + "/income")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequestSerializer.write(batch).getJson())
        );

        params.add("color", "Green");
        params.add("operation", "equal");
        params.add("cottonPart", "50");

        MockHttpServletResponse response = mvc.perform(
                get(urlTemplate).contentType(MediaType.APPLICATION_JSON)
                        .params(params)
        ).andReturn().getResponse();

        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void lessThanGetTest() throws Exception {
        DeliveryBatchSocks batch = new DeliveryBatchSocks("Green", 30, 12314);
        SocksInfo expectedResult = new SocksInfo("12324");

        mvc.perform(post(urlTemplate + "/income")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequestSerializer.write(batch).getJson())
        );

        params.add("color", "Green");
        params.add("operation", "lessThan");
        params.add("cottonPart", "60");

        MockHttpServletResponse response = mvc.perform(
                get(urlTemplate).contentType(MediaType.APPLICATION_JSON)
                        .params(params)
        ).andReturn().getResponse();

        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void moreThanGetTest() throws Exception {
        DeliveryBatchSocks batch = new DeliveryBatchSocks("Green", 90, 2233);
        SocksInfo expectedResult = new SocksInfo("2243");

        mvc.perform(post(urlTemplate + "/income")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequestSerializer.write(batch).getJson())
        );

        params.add("color", "Green");
        params.add("operation", "moreThan");
        params.add("cottonPart", "30");

        MockHttpServletResponse response = mvc.perform(
                get(urlTemplate).contentType(MediaType.APPLICATION_JSON)
                        .params(params)
        ).andReturn().getResponse();

        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void wrongRequestParamsTest() throws Exception {
        //settings a wrong word to request params
        params.add("operation", "aihdfajkssdfh");

        MockHttpServletResponse response = mvc.perform(
                get(urlTemplate).contentType(MediaType.APPLICATION_JSON)
                        .params(params)
        ).andReturn().getResponse();


        then(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}
