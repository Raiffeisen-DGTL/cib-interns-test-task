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
import ru.danilarassokhin.raiffeisensocks.dto.SocksIncomeDto;
import ru.danilarassokhin.raiffeisensocks.dto.SocksOutcomeDto;
import ru.danilarassokhin.raiffeisensocks.dto.SocksSearchDto;
import ru.danilarassokhin.raiffeisensocks.exception.DataValidityException;
import ru.danilarassokhin.raiffeisensocks.exception.InternalException;
import ru.danilarassokhin.raiffeisensocks.repository.SocksRepository;
import ru.danilarassokhin.raiffeisensocks.service.impl.SocksServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.danilarassokhin.raiffeisensocks.Url.API_ENDPOINT;
import static ru.danilarassokhin.raiffeisensocks.Url.SOCKS;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class SocksControllerTest extends IntegrationTest{

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SocksServiceImpl socksService;

    @Autowired
    private SocksRepository socksRepository;

    @BeforeEach
    public void init() throws DataValidityException, InternalException {
        socksRepository.deleteAll();

        SocksIncomeDto socksIncomeDto = new SocksIncomeDto();
        socksIncomeDto.setQuantity(1);
        socksIncomeDto.setColor("red");
        socksIncomeDto.setCottonPart((byte) 10);
        socksService.income(socksIncomeDto);
    }

    @Test
    public void validSocksIncomeNewSocks() throws Exception {
        SocksIncomeDto socksIncomeDto = new SocksIncomeDto();
        socksIncomeDto.setQuantity(1);
        socksIncomeDto.setColor("yellow");
        socksIncomeDto.setCottonPart((byte) 20);

        String json = new Gson().toJson(socksIncomeDto);

        mockMvc.perform(
                post(API_ENDPOINT + SOCKS.ENDPOINT + SOCKS.INCOME)
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
        SocksIncomeDto socksIncomeDto = new SocksIncomeDto();
        socksIncomeDto.setQuantity(1);
        socksIncomeDto.setColor("red");
        socksIncomeDto.setCottonPart((byte) 10);

        String json = new Gson().toJson(socksIncomeDto);

        mockMvc.perform(
                post(API_ENDPOINT + SOCKS.ENDPOINT + SOCKS.INCOME)
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
                post(API_ENDPOINT + SOCKS.ENDPOINT + SOCKS.INCOME)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(
                        status().isBadRequest()
                );
    }

    @Test
    public void validSocksOutcomeExistingSocks() throws Exception {
        SocksOutcomeDto socksOutcomeDto = new SocksOutcomeDto();
        socksOutcomeDto.setQuantity(1);
        socksOutcomeDto.setColor("red");
        socksOutcomeDto.setCottonPart((byte) 10);

        String json = new Gson().toJson(socksOutcomeDto);

        mockMvc.perform(
                post(API_ENDPOINT + SOCKS.ENDPOINT + SOCKS.OUTCOME)
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
        SocksOutcomeDto socksOutcomeDto = new SocksOutcomeDto();
        socksOutcomeDto.setQuantity(2);
        socksOutcomeDto.setColor("red");
        socksOutcomeDto.setCottonPart((byte) 10);

        String json = new Gson().toJson(socksOutcomeDto);

        mockMvc.perform(
                post(API_ENDPOINT + SOCKS.ENDPOINT + SOCKS.OUTCOME)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        )
                .andExpect(
                        status().isBadRequest()
                );
    }

    @Test
    public void validSocksCountEqualsExisting() throws Exception {
        SocksSearchDto socksSearchDto = new SocksSearchDto();
        socksSearchDto.setColor("red");
        socksSearchDto.setOperation("equal");
        socksSearchDto.setCottonPart((byte) 10);

        String json = new Gson().toJson(socksSearchDto);

        mockMvc.perform(
                get(API_ENDPOINT + SOCKS.ENDPOINT)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
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
        SocksSearchDto socksSearchDto = new SocksSearchDto();
        socksSearchDto.setColor("yellow");
        socksSearchDto.setOperation("equal");
        socksSearchDto.setCottonPart((byte) 10);

        String json = new Gson().toJson(socksSearchDto);

        mockMvc.perform(
                get(API_ENDPOINT + SOCKS.ENDPOINT)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(
                        status().isOk()
                )
                .andExpect(
                        jsonPath("$.data").value(0)
                );
    }

}
