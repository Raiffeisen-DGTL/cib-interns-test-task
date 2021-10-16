package com.app.raiffeisen;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import static com.app.raiffeisen.database.Values.*;

public class Test {

    public static void main(String[] args) {

        testSocksIncome("red", 30, 1000);
        testSocksIncome("red", 30, 1500);
        testSocksIncome("red", 50, 600);
        testSocksIncome("yellow", 10, 500);

        testSocksOutcome("red", 30, 100);
        testSocksOutcome("blue", 40, 10000);

        testSocksCount("red", "lessThan", 60);
        testSocksCount("red", "lessThan", 10);
        testSocksCount("orange", "equal", 10);

    }

    private static void testSocksCount(String color, String operation, int cottonPart) {
        sendHTTPRequest(urlSocks + "?color=" + color + "&operation=" + operation + "&cottonPart=" + cottonPart);
    }

    private static void testSocksIncome(String color, int cottonPart, long quantity) {
        JSONObject json = getSocksJSON(color, cottonPart, quantity);
        sendHTTPRequest(urlIncome, json);
    }

    private static void testSocksOutcome(String color, int cottonPart, long quantity) {
        JSONObject json = getSocksJSON(color, cottonPart, quantity);
        sendHTTPRequest(urlOutcome, json);
    }


    private static void sendHTTPRequest(String url) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        try {
            HttpGet request = new HttpGet(url);

            httpClient.execute(request);

            httpClient.close();
        } catch (Exception ignored) { }
    }

    private static void sendHTTPRequest(String url, JSONObject json) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        try {
            HttpPost request = new HttpPost(url);
            StringEntity params = new StringEntity(json.toString());
            request.addHeader("content-type", "application/json");
            request.setEntity(params);

            httpClient.execute(request);

            httpClient.close();
        } catch (Exception ignored) { }
    }

    private static JSONObject getSocksJSON(String color, int cottonPart, long quantity) {
        JSONObject json = new JSONObject();
        try {
            json.put("color", color);
            json.put("cottonPart", cottonPart);
            json.put("quantity", quantity);
        } catch (JSONException ignored) { }

        return json;
    }

}
