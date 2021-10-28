package com.raiffeisensocks.app.service;

import com.raiffeisensocks.app.dto.SocksDto;
import org.springframework.stereotype.Service;

@Service
public interface SocksService {
    void addIncome(SocksDto socksDto);
    void addOutcome(SocksDto socksDto);
    Integer getAllSocksByParams(String color, String operation, Integer cottonPart);
}
