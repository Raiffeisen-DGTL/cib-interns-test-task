package ru.danilarassokhin.raiffeisensocks.integrational;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.danilarassokhin.raiffeisensocks.EmbeddedTest;
import ru.danilarassokhin.raiffeisensocks.dto.SocksIncomeDto;
import ru.danilarassokhin.raiffeisensocks.dto.SocksOutcomeDto;
import ru.danilarassokhin.raiffeisensocks.repository.SocksRepository;
import ru.danilarassokhin.raiffeisensocks.service.dto.ServiceResponseStatus;
import ru.danilarassokhin.raiffeisensocks.service.dto.SocksServiceIncomeDto;
import ru.danilarassokhin.raiffeisensocks.service.impl.SocksServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.danilarassokhin.raiffeisensocks.Url.API_ENDPOINT;
import static ru.danilarassokhin.raiffeisensocks.Url.Socks;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class SocksControllerTest extends EmbeddedTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private SocksServiceImpl socksService;

  @Autowired
  private SocksRepository socksRepository;

  @BeforeEach
  public void init() {
    socksRepository.deleteAll();

    SocksServiceIncomeDto socksIncomeDto = new SocksServiceIncomeDto("red", (byte) 10, 1L);

    socksService.income(socksIncomeDto);
  }

  @Test
  public void validSocksIncomeNewSocks() throws Exception {
    SocksIncomeDto socksIncomeDto = new SocksIncomeDto("yellow", (byte) 20, 1L);

    String json = new Gson().toJson(socksIncomeDto);

    mockMvc.perform(
        post(API_ENDPOINT + Socks.ENDPOINT + Socks.INCOME)
            .content(json)
            .contentType(MediaType.APPLICATION_JSON)
    )
        .andExpect(
            status().isOk()
        )
        .andExpect(
            jsonPath("$.data.color").value("yellow")
        )
        .andExpect(
            jsonPath("$.data.quantity").value(1)
        )
        .andExpect(
            jsonPath("$.data.cottonPart").value(20)
        );
  }

  @Test
  public void validSocksIncomeExistingSocks() throws Exception {
    SocksIncomeDto socksIncomeDto = new SocksIncomeDto("red", (byte) 10, 1L);

    String json = new Gson().toJson(socksIncomeDto);

    mockMvc.perform(
        post(API_ENDPOINT + Socks.ENDPOINT + Socks.INCOME)
            .content(json)
            .contentType(MediaType.APPLICATION_JSON)
    )
        .andExpect(
            status().isOk()
        )
        .andExpect(
            jsonPath("$.data.color").value("red")
        )
        .andExpect(
            jsonPath("$.data.quantity").value(2)
        )
        .andExpect(
            jsonPath("$.data.cottonPart").value(10)
        );
  }

  @Test
  public void invalidSocksIncome() throws Exception {
    SocksIncomeDto socksIncomeDto = new SocksIncomeDto();
    String json = new Gson().toJson(socksIncomeDto);

    mockMvc.perform(
        post(API_ENDPOINT + Socks.ENDPOINT + Socks.INCOME)
            .content(json)
            .contentType(MediaType.APPLICATION_JSON)
    )
        .andExpect(
            status().isBadRequest()
        );
  }

  @Test
  public void validSocksOutcomeExistingSocks() throws Exception {
    SocksOutcomeDto socksOutcomeDto = new SocksOutcomeDto("red", (byte) 10, 1L);

    String json = new Gson().toJson(socksOutcomeDto);

    mockMvc.perform(
        post(API_ENDPOINT + Socks.ENDPOINT + Socks.OUTCOME)
            .content(json)
            .contentType(MediaType.APPLICATION_JSON)
    )
        .andExpect(
            status().isOk()
        )
        .andExpect(
            jsonPath("$.data.color").value("red")
        )
        .andExpect(
            jsonPath("$.data.quantity").value(0)
        )
        .andExpect(
            jsonPath("$.data.cottonPart").value(10)
        );
  }

  @Test
  public void validSocksOutcomeMoreThanInStock() throws Exception {
    SocksOutcomeDto socksOutcomeDto = new SocksOutcomeDto("red", (byte) 10, 2L);

    String json = new Gson().toJson(socksOutcomeDto);

    mockMvc.perform(
        post(API_ENDPOINT + Socks.ENDPOINT + Socks.OUTCOME)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json)
    )
        .andExpect(
            status().isOk()
        )
        .andExpect(
            jsonPath("$.status").value(ServiceResponseStatus.NO_SOCKS_LEFT.name())
        );
  }

  @Test
  public void validSocksCountEqualsExisting() throws Exception {
    mockMvc.perform(
        get(API_ENDPOINT + Socks.ENDPOINT)
            .param("color", "red")
            .param("operation", "equal")
            .param("cottonPart", "10")
    )
        .andExpect(
            status().isOk()
        )
        .andExpect(
            jsonPath("$.data").value(1)
        );
  }

  @Test
  public void validSocksCountEqualsNotExisting() throws Exception {
    mockMvc.perform(
        get(API_ENDPOINT + Socks.ENDPOINT)
            .param("color", "yellow")
            .param("operation", "equal")
            .param("cottonPart", "10")
    )
        .andExpect(
            status().isOk()
        )
        .andExpect(
            jsonPath("$.data").value(0)
        );
  }

  @Test
  public void socksCountInvalidOperation() throws Exception {
    mockMvc.perform(
        get(API_ENDPOINT + Socks.ENDPOINT)
            .param("color", "yellow")
            .param("operation", "wrong")
            .param("cottonPart", "10")
    )
        .andExpect(
            status().isBadRequest()
        );
  }

  @Test
  public void countSocksEqualMustReturnQuantity() throws Exception {
    mockMvc.perform(
        get(API_ENDPOINT + Socks.ENDPOINT)
            .param("color", "red")
            .param("operation", "equal")
            .param("cottonPart", "10")
    )
        .andExpect(
            status().isOk()
        )
        .andExpect(
            jsonPath("$.data").value(1)
        );
  }

}
