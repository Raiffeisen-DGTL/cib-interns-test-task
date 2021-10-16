package ru.raiffeisen.core.api;

import org.springframework.http.ResponseEntity;
import ru.raiffeisen.core.model.StorageInfoModel;

/**
 * Сервис для работы со складом носков.
 */
public interface StorageService {

    /**
     * Метод для обновления информации в БД.
     * @param storageInfoModel - информация о приходе:
     *                            * цвет носков;
     *                            * процентное содержание хлопка в носках;
     *                            * количество пар носков в приходе.
     * @return - HTTP Response с вложенным сообщением
     */
    ResponseEntity<String> updateStorageInfo(StorageInfoModel storageInfoModel, String operation);

    /**
     * Метод для подсчета количества пар носков на складе по переданным параметрам.
     * @param color - цвет носков;
     * @param operation - оператор сравнения значения количества хлопка в составе
     *                    носков, одно значение из:
     *                       * moreThan;
     *                       * lessThan;
     *                       * equal;
     * @param cottonPart - процентное содержание хлопка в составе носков.
     * @return - HTTP Response с искомым количеством.
     */
    ResponseEntity<String> getQuantityByParams(String color, String operation, int cottonPart);

    /**
     * Метод для проверки валидности переданных в теле запроса параметров.
     * @param storageInfoModel - информация о приходе:
     *                            * цвет носков;
     *                            * процентное содержание хлопка в носках;
     *                            * количество пар носков в приходе.
     * @return - HTTP Response с вложенным сообщением
     */
    ResponseEntity<String> verifyParams(StorageInfoModel storageInfoModel);
}
