package com.zpsx.cibinternstesttask.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.zpsx.cibinternstesttask.domain.Operation;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;

public class TestUtils {

    public static ResponseEntity<Object> getSockStockQuantity(String color,
                                                              byte cottonPart,
                                                              Operation operation,
                                                              TestRestTemplate restTemplate,
                                                              int randomServerPort) throws URISyntaxException {
        URI uri = new URI(String.format("http://localhost:%s/api/socks?color=%s&operation=%s&cottonPart=%d",
                randomServerPort, color, operation, cottonPart));
        return restTemplate.getForEntity(uri, Object.class);

    }
    public static ResponseEntity<Object> postIncome(String color,
                                                    byte cottonPart,
                                                    long quantity,
                                                    TestRestTemplate restTemplate,
                                                    int randomServerPort
                                                    ) throws JsonProcessingException, URISyntaxException {

        URI uri = new URI("http://localhost:"+randomServerPort+"/api/socks/income");
        HttpEntity<String> request = getSockStockHttpEntity(color, cottonPart, quantity);
        return restTemplate.postForEntity(uri, request, Object.class);
    }

    public static ResponseEntity<Object> postOutcome(String color,
                                                    byte cottonPart,
                                                    long quantity,
                                                    TestRestTemplate restTemplate,
                                                    int randomServerPort
    ) throws JsonProcessingException, URISyntaxException {

        URI uri = new URI("http://localhost:"+randomServerPort+"/api/socks/outcome");
        HttpEntity<String> request = getSockStockHttpEntity(color, cottonPart, quantity);
        return restTemplate.postForEntity(uri, request, Object.class);
    }

    public static HttpEntity<String> getSockStockHttpEntity(String color,
                                                            byte cottonPart,
                                                            long quantity) throws JsonProcessingException {
        String jsonString = getSockStockBodyString(color, cottonPart, quantity);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(jsonString, headers);
    }

    private static String getSockStockBodyString(String color,
                                                 byte cottonPart,
                                                 long quantity) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.put("color", color);
        rootNode.put("cottonPart", cottonPart);
        rootNode.put("quantity", quantity);

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
    }
}
