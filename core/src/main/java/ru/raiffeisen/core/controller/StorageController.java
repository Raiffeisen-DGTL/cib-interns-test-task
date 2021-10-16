package ru.raiffeisen.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.raiffeisen.core.api.StorageService;
import ru.raiffeisen.core.model.ColorInfoModel;
import ru.raiffeisen.core.model.SocksInfoModel;
import ru.raiffeisen.core.model.StorageInfoModel;
import ru.raiffeisen.core.repository.ColorsRepository;
import ru.raiffeisen.core.repository.StorageRepository;

import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api")
public class StorageController {

    @Autowired
    private StorageService storageService;

    private final String OPERATION_INCOME = "income";
    private final String OPERATION_OUTCOME = "outcome";

    @PostMapping("/socks/income")
    public ResponseEntity<String> income(@RequestBody StorageInfoModel storageInfoModel) {

        log.info("Received post request to update storage with income info - {}", storageInfoModel);

        ResponseEntity<String> parameterVerificationResults = storageService.verifyParams(storageInfoModel);
        if (parameterVerificationResults.getStatusCode().is4xxClientError()) {

            return parameterVerificationResults;
        } else {

            return storageService.updateStorageInfo(storageInfoModel, OPERATION_INCOME);
        }
    }

    @PostMapping("/socks/outcome")
    public ResponseEntity<String> outcome(@RequestBody StorageInfoModel storageInfoModel) {

        log.info("Received post request to update storage with outcome info - {}", storageInfoModel);

        ResponseEntity<String> parameterVerificationResults = storageService.verifyParams(storageInfoModel);
        if (parameterVerificationResults.getStatusCode().is4xxClientError()) {

            return parameterVerificationResults;
        } else {

            return storageService.updateStorageInfo(storageInfoModel, OPERATION_OUTCOME);
        }
    }

    @GetMapping("/socks") //возможно возвращает инт?
    public ResponseEntity<String> getQuantityByParams(@RequestParam String color, @RequestParam String operation, @RequestParam int cottonPart) {

        log.info("Received get request to count pair of socks by params: Color - {}, Operation - {}, Cotton Part - {}", color, operation, cottonPart);

        return storageService.getQuantityByParams(color, operation, cottonPart);
    }
}
