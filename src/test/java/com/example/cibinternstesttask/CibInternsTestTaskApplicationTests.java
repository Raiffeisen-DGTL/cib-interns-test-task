package com.example.cibinternstesttask;

import com.example.cibinternstesttask.repository.SockRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class CibInternsTestTaskApplicationTests {

	SockRepository sockRepository;

	@Autowired
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		sockRepository = Mockito.mock(SockRepository.class);
	}

	@Test
	public void testIncome() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders
						.post("/api/socks/income")
						.content("{\"color\": \"black\", \"cottonPart\" : \"50\", \"quantity\" : \"30\"}")
						.contentType(MediaType.APPLICATION_JSON)
				).andExpect(MockMvcResultMatchers.status().isOk());

		mockMvc.perform(
				MockMvcRequestBuilders
						.post("/api/socks/income")
						.content("{\"coor\": \"black\", \"cottonPart\" : \"50\", \"quantity\" : \"30\"}")
						.contentType(MediaType.APPLICATION_JSON)
		).andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void testOutcome() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders
						.post("/api/socks/income")
						.content("{\"color\": \"black\", \"cottonPart\" : \"50\", \"quantity\" : \"30\"}")
						.contentType(MediaType.APPLICATION_JSON)
		).andExpect(MockMvcResultMatchers.status().isOk());

		mockMvc.perform(
				MockMvcRequestBuilders
						.post("/api/socks/outcome")
						.content("{\"color\": \"black\", \"cottonPart\" : \"50\", \"quantity\" : \"10\"}")
						.contentType(MediaType.APPLICATION_JSON)
		).andExpect(MockMvcResultMatchers.status().isOk());

		mockMvc.perform(
				MockMvcRequestBuilders
						.post("/api/socks/outcome")
						.content("{\"color\": \"black\", \"cottonPart\" : \"50\", \"quantity\" : \"10000\"}")
						.contentType(MediaType.APPLICATION_JSON)
		).andExpect(MockMvcResultMatchers.status().isBadRequest());

		mockMvc.perform(
				MockMvcRequestBuilders
						.post("/api/socks/outcome")
						.content("{\"coor\": \"black\", \"cottonPart\" : \"50\", \"quantity\" : \"10\"}")
						.contentType(MediaType.APPLICATION_JSON)
		).andExpect(MockMvcResultMatchers.status().isBadRequest());

		mockMvc.perform(
				MockMvcRequestBuilders
						.post("/api/socks/outcome")
						.content("{\"color\": \"white\", \"cottonPart\" : \"50\", \"quantity\" : \"10\"}")
						.contentType(MediaType.APPLICATION_JSON)
		).andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void testGetSocks() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders
						.post("/api/socks/income")
						.content("{\"color\": \"black\", \"cottonPart\" : \"50\", \"quantity\" : \"30\"}")
						.contentType(MediaType.APPLICATION_JSON)
		).andExpect(MockMvcResultMatchers.status().isOk());

		mockMvc.perform(
				MockMvcRequestBuilders
						.get("/api/socks")
						.param("color", "black")
						.param("operation", "lessThan")
						.param("cottonPart", "60")
						.contentType(MediaType.APPLICATION_JSON)
		).andExpect(MockMvcResultMatchers.status().isOk());

		mockMvc.perform(
				MockMvcRequestBuilders
						.get("/api/socks")
						.param("coor", "black")
						.param("operation", "lessThan")
						.param("cottonPart", "60")
						.contentType(MediaType.APPLICATION_JSON)
		).andExpect(MockMvcResultMatchers.status().isBadRequest());

		mockMvc.perform(
				MockMvcRequestBuilders
						.get("/api/socks")
						.param("color", "blck")
						.param("operation", "lessThan")
						.param("cottonPart", "60")
						.contentType(MediaType.APPLICATION_JSON)
		).andExpect(MockMvcResultMatchers.status().isOk());
	}
}
