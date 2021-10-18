package ru.akkulov.raiffeisen.service;

import ru.akkulov.raiffeisen.exception.SockIncorrectDataException;
import ru.akkulov.raiffeisen.model.Sock;
import ru.akkulov.raiffeisen.util.Operation;

import java.util.List;

public interface SockService {

    /**
     * Возвращает партию Sock по цвету и процентному содержанию хлопка.
     *
     * @param comingSock объект класса {@link Sock}
     *
     * @return объект типа {@link Sock} с измененным состоянием
     *
     * @throws SockIncorrectDataException exc
     */
    Sock getSockByColorAndCottonPart(Sock comingSock);

    /**
     * Метод возвращает общее количество всех носков (quantity) со всех партий
     *
     * @param color      цвет
     * @param operation  'moreThan', 'lessThan', 'equal'
     * @param cottonPart процентное содержание хлопка в носках
     *
     * @return {@link String} общее количество всех носков на складе, удовлетворяющее веденным параметрам
     */
    String getQuantityByParameters(String color, Operation operation, int cottonPart);

    /**
     * Создает партию носков Sock.
     *
     * @param sock {@link Sock}
     * @throws SockIncorrectDataException exc
     */
    Sock createSock(Sock sock);

    /**
     * Возвращает партию носков Sock по id.
     *
     * @param sockId идентификатор
     *
     * @return {@link Sock}
     */
    Sock getSocksById(long sockId);

    /**
     * Удаляет партию носков Sock по id.
     *
     * @param sockId идентификатор
     */
    void deleteSocksById(long sockId);

    /**
     * Возвращает список всех партий носков {@link Sock}
     *
     * @return List<Sock>
     */
    List<Sock> getAllSocks();
}
