package com.warehouse.storewarehouse;

import com.warehouse.storewarehouse.counting.DeliveryBatchSocks;
import com.warehouse.storewarehouse.counting.SocksService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest
class StoreWarehouseApplicationTests {


	@MockBean
	private SocksService socksService;

	@Autowired
	private MockMvc mvc;

	@Autowired
	private JacksonTester<DeliveryBatchSocks> jsonRequestAttempt;

	private final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

	@Test
	public void complexIncomeAndOutComeTest1() throws Exception {

		DeliveryBatchSocks delivery = new DeliveryBatchSocks("Brown", 77, 99);

		String urlTemplate = "/api/socks";
		MockHttpServletResponse response = mvc.perform(
				post(urlTemplate + "/income").contentType(MediaType.APPLICATION_JSON)
						.content(jsonRequestAttempt.write(delivery).getJson())
		).andReturn().getResponse();

		then(response.getStatus()).isEqualTo(HttpStatus.OK.value());

		DeliveryBatchSocks delivery2 = new DeliveryBatchSocks("Brown", 77, 90);

		MockHttpServletResponse response2 = mvc.perform(
				post(urlTemplate + "/outcome").contentType(MediaType.APPLICATION_JSON)
						.content(jsonRequestAttempt.write(delivery2).getJson())
		).andReturn().getResponse();

		then(response2.getStatus()).isEqualTo(HttpStatus.OK.value());

		params.add("color", "Brown");
		params.add("operation", "equal");
		params.add("cottonPart", "77");

		MockHttpServletResponse response3 = mvc.perform(
				get(urlTemplate).contentType(MediaType.APPLICATION_JSON)
						.params(params)
		).andReturn().getResponse();

		then(response3.getStatus()).isEqualTo(HttpStatus.OK.value());
	}
}
