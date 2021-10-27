package com.example.socksApi.service;

import com.example.socksApi.dto.IncomeDto;
import com.example.socksApi.dto.OutcomeDto;

public interface SockService {

    void income(IncomeDto sock);

    void outcome(OutcomeDto sock);

    int count(String color, String comparison, int cottonPart);

}