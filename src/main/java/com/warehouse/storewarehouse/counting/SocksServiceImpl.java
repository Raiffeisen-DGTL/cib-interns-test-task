package com.warehouse.storewarehouse.counting;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class SocksServiceImpl implements SocksService {

    private final SocksRepository socksRepository;

    /**
     * Метод регистрируем поставку в новых товаров, если записи в БД на момент поставки не существует.
     * Она будет автоматически создана
     *
     * @param delivery Новая поставка носков
     * @return Сообщение об успешной поставке с новым количеством товара на складе
     */
    @Override
    public SimpleResponse registerIncome(DeliveryBatchSocks delivery) {

        SocksRecords record = socksRepository
                .getSocksRecordsByColorAndcottonPart(delivery.getColor(), delivery.getCottonPart())
                .orElseGet(() -> {
                    log.info("Creating new socks record with params: Color - {}, Cotton Part - {}, Quantity - {}",
                            delivery.getColor(),
                            delivery.getCottonPart(),
                            delivery.getQuantity());
                    return socksRepository.save(
                            new SocksRecords(delivery.getColor(), 0, delivery.getCottonPart())
                    );
                });

        socksRepository.setNewQuantityForSocksRecord(
                record.getQuantity() + delivery.getQuantity(),
                delivery.getColor(),
                delivery.getCottonPart());

        return new SimpleResponse("New delivery successfully registered. New amount: " + (record.getQuantity() + delivery.getQuantity()) + ".");
    }

    /**
     * Метод регистрирует уход товара со склада.
     * Если товара на складе меньше, чем в заявке будет вызвано исключение
     * Если товара на складе не существует, будет вызвано исключение
     *
     * @return Сообщение об успешной регистрации убытия товара с новым количеством товара на складе
     */
    @Override
    public SimpleResponse registerOutcome(DeliveryBatchSocks delivery) {

        SocksRecords record = socksRepository.getSocksRecordsByColorAndcottonPart(
                delivery.getColor(),
                delivery.getCottonPart()
        ).orElseThrow(
                () -> {
                    log.info("Requested items do not exists");
                    throw new NoSuchElementException("No such element");
                }
        );

        if (record.getQuantity() >= delivery.getQuantity()) {
            socksRepository.setNewQuantityForSocksRecord(
                    record.getQuantity() - delivery.getQuantity(),
                    record.getColor(),
                    record.getCottonPart()
            );
        } else {
            String message = "Requested socks item was found but it's quantity was not enough";
            log.info(message);
            throw new RuntimeException(message);
        }

        return new SimpleResponse("New delivery successfully registered. New amount: " + (record.getQuantity() - delivery.getQuantity()) + ".");
    }

    /**
     * Возвращает количество пар носков по входящим параметрам запроса
     *
     * @param color      цвет носков
     * @param operation  операция, по которой будет формироваться результат
     * @param cottonPart процент хлопка в носках
     * @return количество пар, удовлетворяющих условию
     */
    @Override
    public SocksInfo getInfo(String color, String operation, String cottonPart) {
        int result = 0;
        try {
            if (operation.equalsIgnoreCase("equal")) {
                result = socksRepository.findSocksRecordsByColorEquals(color, Integer.parseInt(cottonPart));
            } else if (operation.equalsIgnoreCase("lessThan")) {
                result = socksRepository.findSocksRecordsByColorAndLessThanCottonPart(color, Integer.parseInt(cottonPart));
            } else if (operation.equalsIgnoreCase("moreThan")) {
                result = socksRepository.findSocksRecordsByColorAndMoreThanCottonPart(color, Integer.parseInt(cottonPart));
            } else {
                log.info("Cannot process the request: Param operation must be \"equal\", \"lessThan\" or \"moreThan\"");
                throw new IllegalArgumentException("Param operation must be \"equal\", \"lessThan\" or \"moreThan\"");
            }
        } catch (Exception e) {
            log.info(e.toString());
        }
        return new SocksInfo(Integer.toString(result));
    }
}
