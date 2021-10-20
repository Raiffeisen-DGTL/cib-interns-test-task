package com.raif.app.service;


import com.raif.app.controller.dto.SocksIncomeDTO;
import com.raif.app.controller.dto.SocksOutcomeDTO;

public interface SocksStorageService {

    void registerIncome(SocksIncomeDTO socksIncomeDTO);

    void registerOutcome(SocksOutcomeDTO socksIncomeDTO);

    Long find(SocksStorageRequestFilter socksStorageRequestFilter);

}
