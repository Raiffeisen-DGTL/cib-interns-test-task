package controllers;


import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import raiffeisen.controllers.SocksController;
import raiffeisen.models.socks.Socks;
import raiffeisen.services.SocksService;
import raiffeisen.utils.Operator;

/**
 * @author voroningg
 */
public class SocksControllerTests {
    private static final String BASE_URL = "/api/socks";
    private static final String CREATE_TABLE = "/create_table";
    private static final String INCOME = "/income";
    private static final String OUTCOME = "/outcome";
    private static final String COLOR = "color=";
    private static final String OPERATION = "operation=";
    private static final String COTTON_PART = "cottonPart=";
    private static final String LESS_THAN = "lessThan";
    private static final String MORE_THAN = "moreThan";
    private static final String EQUAL = "equal";

    private static final Socks DEFAULT_SOCKS = new Socks("red", 10, 10);
    private static final Socks SOCKS_WITH_EMPTY_COLOR = new Socks("", 10, 10);
    private static final Socks SOCKS_WITH_NULL_COLOR = new Socks(null, 10, 10);
    private static final Socks SOCKS_WITH_NEGATIVE_COTTON_PART = new Socks("red", -1, 10);
    private static final Socks SOCKS_WITH_ZERO_COTTON_PART = new Socks("red", 0, 10);
    private static final Socks SOCKS_WITH_HUNDRED_COTTON_PART = new Socks("red", 100, 10);
    private static final Socks SOCKS_WITH_HUNDRED_ONE_COTTON_PART = new Socks("red", 101, 10);
    private static final Socks SOCKS_WITH_NEGATIVE_QUANTITY = new Socks("red", 10, -1);
    private static final Socks SOCKS_WITH_ZERO_QUANTITY = new Socks("red", 10, 0);
    private static final Socks SOCKS_WITH_POSITIVE_QUANTITY = new Socks("red", 10, 1);

    @InjectMocks
    private SocksController socksController;

    @Mock
    private SocksService socksService;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() throws Exception {
        MockitoAnnotations.openMocks(this);
        Mockito.doNothing().doThrow(Mockito.mock(BadSqlGrammarException.class)).when(socksService).createTable();
        Mockito.doNothing().when(socksService).income(DEFAULT_SOCKS);
        Mockito.when(socksService.countFiltered("red", Operator.MoreThan, 10))
                .thenReturn(10);
        Mockito.when(socksService.countFiltered("black", Operator.MoreThan, 10))
                .thenReturn(0);
        mockMvc = MockMvcBuilders.standaloneSetup(socksController).build();
    }

    @Test
    public void shouldReturnOkResultOnCreateTableInFirstTime() throws Exception {
        MockHttpServletResponse response = postResponse(
                String.format("%s%s", BASE_URL, CREATE_TABLE));
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void shouldReturnConflictResultOnCreateTableTwice() throws Exception {
        MockHttpServletResponse response1 = postResponse(
                String.format("%s%s", BASE_URL, CREATE_TABLE));
        MockHttpServletResponse response2 = postResponse(
                String.format("%s%s", BASE_URL, CREATE_TABLE));
        Assertions.assertEquals(HttpStatus.OK.value(), response1.getStatus());
        Assertions.assertEquals(HttpStatus.CONFLICT.value(), response2.getStatus());
    }

    @ParameterizedTest
    @ValueSource(strings = {INCOME, OUTCOME})
    public void methodShouldReturnOkResultOnValidSocks(String method) throws Exception {
        MockHttpServletResponse response = postResponse(
                String.format("%s%s", BASE_URL, method),
                new Gson().toJson(DEFAULT_SOCKS));
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @ParameterizedTest
    @ValueSource(strings = {INCOME, OUTCOME})
    public void methodShouldReturnBadRequestOnEmptyColor(String method) throws Exception {
        MockHttpServletResponse response = postResponse(
                String.format("%s%s", BASE_URL, method),
                new Gson().toJson(SOCKS_WITH_EMPTY_COLOR));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @ParameterizedTest
    @ValueSource(strings = {INCOME, OUTCOME})
    public void methodShouldReturnBadRequestOnNullColor(String method) throws Exception {
        MockHttpServletResponse response = postResponse(
                String.format("%s%s", BASE_URL, method),
                new Gson().toJson(SOCKS_WITH_NULL_COLOR));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @ParameterizedTest
    @ValueSource(strings = {INCOME, OUTCOME})
    public void methodShouldReturnBadRequestOnNegativeCottonPart(String method) throws Exception {
        MockHttpServletResponse response = postResponse(
                String.format("%s%s", BASE_URL, method),
                new Gson().toJson(SOCKS_WITH_NEGATIVE_COTTON_PART));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @ParameterizedTest
    @ValueSource(strings = {INCOME, OUTCOME})
    public void methodShouldReturnBadRequestOnCottonPartGreaterThan100(String method) throws Exception {
        MockHttpServletResponse response = postResponse(
                String.format("%s%s", BASE_URL, method),
                new Gson().toJson(SOCKS_WITH_HUNDRED_ONE_COTTON_PART));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @ParameterizedTest
    @ValueSource(strings = {INCOME, OUTCOME})
    public void methodShouldReturnBadRequestOnNegativeQuantity(String method) throws Exception {
        MockHttpServletResponse response = postResponse(
                String.format("%s%s", BASE_URL, method),
                new Gson().toJson(SOCKS_WITH_NEGATIVE_QUANTITY));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @ParameterizedTest
    @ValueSource(strings = {INCOME, OUTCOME})
    public void methodShouldReturnOkOnZeroQuantity(String method) throws Exception {
        MockHttpServletResponse response = postResponse(
                String.format("%s%s", BASE_URL, method),
                new Gson().toJson(SOCKS_WITH_ZERO_QUANTITY));
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @ParameterizedTest
    @ValueSource(strings = {INCOME, OUTCOME})
    public void methodShouldReturnOkOnZeroCottonPart(String method) throws Exception {
        MockHttpServletResponse response = postResponse(
                String.format("%s%s", BASE_URL, method),
                new Gson().toJson(SOCKS_WITH_ZERO_COTTON_PART));
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }


    @ParameterizedTest
    @ValueSource(strings = {INCOME, OUTCOME})
    public void methodShouldReturnOkOnHundredCottonPart(String method) throws Exception {
        MockHttpServletResponse response = postResponse(
                String.format("%s%s", BASE_URL, method),
                new Gson().toJson(SOCKS_WITH_HUNDRED_COTTON_PART));
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }


    @ParameterizedTest
    @ValueSource(strings = {INCOME, OUTCOME})
    public void methodShouldReturnOkOnPositiveQuantity(String method) throws Exception {
        MockHttpServletResponse response = postResponse(
                String.format("%s%s", BASE_URL, method),
                new Gson().toJson(SOCKS_WITH_POSITIVE_QUANTITY));
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void outcomeShouldReturnBadRequestOnOutcomeIllegalArgument() throws Exception {
        Mockito.doThrow(IllegalArgumentException.class).when(socksService).outcome(DEFAULT_SOCKS);
        MockHttpServletResponse response = postResponse(
                String.format("%s%s", BASE_URL, OUTCOME),
                new Gson().toJson(DEFAULT_SOCKS));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void getSocksCountShouldReturnBadRequestWithoutColorParam() throws Exception {
        MockHttpServletResponse response = getResponse(
                String.format("%s?%s%s&%s%s", BASE_URL, OPERATION, MORE_THAN, COTTON_PART, 10));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void getSocksCountShouldReturnBadRequestWithEmptyColor() throws Exception {
        MockHttpServletResponse response = getResponse(
                String.format("%s?%s&%s%s&%s%s", BASE_URL, COLOR, OPERATION, MORE_THAN, COTTON_PART, 10));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void getSocksCountShouldReturnOkWithNotEmptyColor() throws Exception {
        MockHttpServletResponse response = getResponse(
                String.format("%s?%s%s&%s%s&%s%s", BASE_URL, COLOR, "red", OPERATION, MORE_THAN, COTTON_PART, 10));
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void getSocksCountShouldReturnBadRequestWithoutOperationParam() throws Exception {
        MockHttpServletResponse response = getResponse(
                String.format("%s?%s%s&%s%s", BASE_URL, COLOR, "red", COTTON_PART, 10));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void getSocksCountShouldReturnBadRequestWithUnknownOperation() throws Exception {
        MockHttpServletResponse response = getResponse(
                String.format("%s?%s%s&%s%s&%s%s", BASE_URL, COLOR, "red", OPERATION, "unknown", COTTON_PART, 10));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void getSocksCountShouldReturnOkWithMoreThanOperation() throws Exception {
        MockHttpServletResponse response = getResponse(
                String.format("%s?%s%s&%s%s&%s%s", BASE_URL, COLOR, "red", OPERATION, MORE_THAN, COTTON_PART, 10));
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void getSocksCountShouldReturnOkWithLessThanOperation() throws Exception {
        MockHttpServletResponse response = getResponse(
                String.format("%s?%s%s&%s%s&%s%s", BASE_URL, COLOR, "red", OPERATION, LESS_THAN, COTTON_PART, 10));
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void getSocksCountShouldReturnOkWithEqualOperation() throws Exception {
        MockHttpServletResponse response = getResponse(
                String.format("%s?%s%s&%s%s&%s%s", BASE_URL, COLOR, "red", OPERATION, EQUAL, COTTON_PART, 10));
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void getSocksCountShouldReturnBadRequestWithoutCottonPartParam() throws Exception {
        MockHttpServletResponse response = getResponse(
                String.format("%s?%s%s&%s%s", BASE_URL, COLOR, "red", OPERATION, MORE_THAN));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void getSocksCountShouldReturnOkWithZeroCountOnEmptyFilteredResult() throws Exception {
        MockHttpServletResponse response = getResponse(
                String.format("%s?%s%s&%s%s&%s%s", BASE_URL, COLOR, "black", OPERATION, MORE_THAN, COTTON_PART, 10));
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(0, Integer
                .parseInt(new Gson().fromJson(response.getContentAsString(), String.class)));
    }

    @Test
    public void getSocksCountShouldReturnOkWithSocksCountOnNotEmptyFilteredResult() throws Exception {
        MockHttpServletResponse response = getResponse(
                String.format("%s?%s%s&%s%s&%s%s", BASE_URL, COLOR, "red", OPERATION, MORE_THAN, COTTON_PART, 10));
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(10, Integer
                .parseInt(new Gson().fromJson(response.getContentAsString(), String.class)));
    }

    private MockHttpServletResponse postResponse(String url, String content) throws Exception {
        return mockMvc
                .perform(MockMvcRequestBuilders.post(url).content(content).contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
    }

    private MockHttpServletResponse postResponse(String url) throws Exception {
        return mockMvc
                .perform(MockMvcRequestBuilders.post(url))
                .andReturn()
                .getResponse();
    }

    private MockHttpServletResponse getResponse(String url) throws Exception {
        return mockMvc
                .perform(MockMvcRequestBuilders.get(url))
                .andReturn()
                .getResponse();
    }
}
