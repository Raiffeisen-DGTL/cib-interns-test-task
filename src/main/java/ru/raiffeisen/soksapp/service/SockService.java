package ru.raiffeisen.soksapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.raiffeisen.soksapp.entity.SockEntity;
import ru.raiffeisen.soksapp.exception.NotEnoughSocsException;
import ru.raiffeisen.soksapp.model.Operation;
import ru.raiffeisen.soksapp.model.Socks;
import ru.raiffeisen.soksapp.repositary.SocksRepository;

import javax.transaction.Transactional;

@Service
public class SockService {

    private static final Logger log = LoggerFactory.getLogger(SockService.class);

    @Autowired
    private SocksRepository repository;

    @Transactional
    public void addNewSocks(Socks sock) {
        // 1. Достает из базы носки с таким color и cottonPart
        SockEntity sockEntity = repository.findSocks(sock.getColor(), sock.getCottonPart());
        if (sockEntity == null) {
            // 2. Если носков нет, то создаем новый носок
            repository.save(new SockEntity(sock));
        } else {
            // 3. Если носки есть, то quantity + sock.quantity
            sockEntity.setQuantity(sockEntity.getQuantity() + sock.getQuantity());
            log.info("Увеличино количество носков: " + sockEntity);
            repository.save(sockEntity);
        }
    }

    @Transactional
    public void deleteSocks(Socks sock) {
        SockEntity sockEntity = repository.findSocks(sock.getColor(), sock.getCottonPart());
        if (sockEntity == null) {
            log.info("Таких носков в базе нету: " + sock);
            return;
        }

        int newQuantity = sockEntity.getQuantity() - sock.getQuantity();

        if (newQuantity < 0) {
            throw new NotEnoughSocsException(String.format("Невозможно удалить: %d потому что в базе всего: %d",
                    sock.getQuantity(), sockEntity.getQuantity()));
        }

        if (newQuantity == 0) {
            log.info("Количество носков стало равным 0, удяляем запись с id: " + sockEntity.getId());
            repository.deleteById(sockEntity.getId());
            return;
        }

        sockEntity.setQuantity(newQuantity);
        repository.save(sockEntity);
    }


    public int findSocksByCriteria(String color, Operation operation, int cottonPart) {
        switch (operation) {
            case EQUALS:
                return repository.findSocks(color, cottonPart).getQuantity();
            case LESS_THAN:
                return repository.findSocksCottonPartLessThan(color, cottonPart)
                        .stream()
                        .mapToInt(SockEntity::getQuantity)
                        .sum();

            case MORE_THAN: // че-то
                return repository.findSocksCottonPartMoreThan(color, cottonPart)
                        .stream()
                        .mapToInt(SockEntity::getQuantity)
                        .sum();
        }

        throw new RuntimeException("Unsupported operation is passed: " + operation);
    }
}
