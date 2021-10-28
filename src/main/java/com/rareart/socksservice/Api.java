package com.rareart.socksservice;

public interface Api {
    String BASE_ROUTE = "/api";
    String SERVICE_ROUTE = "/socks";

    interface Service {
        String INCOME_ROUTE = "/income";
        String OUTCOME_ROUTE = "/outcome";
    }
}
