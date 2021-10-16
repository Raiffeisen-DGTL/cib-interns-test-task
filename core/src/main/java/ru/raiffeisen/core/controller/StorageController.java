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

    /**
     * Контроллер для регистрации прихода носков на склад.
     * @param storageInfoModel - информация о приходе:
     *                            * цвет носков;
     *                            * процентное содержание хлопка в носках;
     *                            * количество пар носков в приходе.
     * @return - HTTP Response с вложенным сообщением
     */
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

    /**
     * Контроллер для регистрации отпуска носков со склада.
     * @param storageInfoModel - информация о приходе:
     *                            * цвет носков;
     *                            * процентное содержание хлопка в носках;
     *                            * количество пар отпускаемых носков.
     * @return - HTTP Response с вложенным сообщением
     */
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

    /**
     * Контроллер для подсчета общего количества носков на складе с переданными параметрами.
     * @param color - цвет носков;
     * @param operation - оператор сравнения значения количества хлопка в составе
     *                    носков, одно значение из:
     *                       * moreThan;
     *                       * lessThan;
     *                       * equal;
     * @param cottonPart - процентное содержание хлопка в составе носков.
     * @return - HTTP Response с искомым количеством.
     */
    @GetMapping("/socks")
    public ResponseEntity<String> getQuantityByParams(@RequestParam String color, @RequestParam String operation, @RequestParam int cottonPart) {

        log.info("Received get request to count pair of socks by params: Color - {}, Operation - {}, Cotton Part - {}", color, operation, cottonPart);

        return storageService.getQuantityByParams(color, operation, cottonPart);
    }
}
