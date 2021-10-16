package ru.raiffeisen.core.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.raiffeisen.core.model.StorageInfoModel;

public interface StorageService {

    ResponseEntity<String> updateStorageInfo(StorageInfoModel storageInfoModel, String operation);

    ResponseEntity<String> getQuantityByParams(String color, String operation, int cottonPart);

    ResponseEntity<String> verifyParams(StorageInfoModel storageInfoModel);
}
