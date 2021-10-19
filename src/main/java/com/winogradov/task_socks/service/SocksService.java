package com.winogradov.task_socks.service;

import com.winogradov.task_socks.Exception.InvalidOperationException;
import com.winogradov.task_socks.Exception.NotEnoughSocksException;
import com.winogradov.task_socks.model.Socks;
import com.winogradov.task_socks.repository.SocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class SocksService {

    private final SocksRepository repository;

    @Autowired
    public SocksService(SocksRepository repository) {
        this.repository = repository;
    }

    public void addSock(String color, Integer cottonPart, Integer quantity) {
        Optional<Socks> optionalSocks = repository.findSocksByColorAndCottonPart(color, cottonPart);

        optionalSocks.ifPresentOrElse(
                socks -> {
                    socks.setQuantity(socks.getQuantity() + quantity);
                    repository.save(socks);
                },
                () -> repository.save(new Socks(null, color, cottonPart, quantity))
        );
    }

    public void releaseSocks(String color, Integer cottonPart, Integer quantity)
            throws NotEnoughSocksException {
        Optional<Socks> socksOptional = repository.findSocksByColorAndCottonPartAndQuantityIsGreaterThanEqual(color, cottonPart, quantity);

        Socks socks = socksOptional.orElseThrow(
                () -> new NotEnoughSocksException(String.valueOf(quantity)));

        socks.setQuantity(socks.getQuantity() - quantity);
        repository.save(socks);
    }

    public String getSocksByParameters(String color, Integer cottonPart, String operation) {
        if (Objects.equals(operation, "moreThan")) {
            Long number = repository.getTotalNumberByColorAndCottonPartMoreThan(color, cottonPart);

            return ifNullReturn(number);

        } else if (Objects.equals(operation, "lessThan")) {
            Long number = repository.getTotalNumberByColorAndCottonPartLessThan(color, cottonPart);

            return ifNullReturn(number);

        } else if (Objects.equals(operation, "equal")) {
            Long number = repository.getTotalNumberByColorAndCottonPartEqual(color, cottonPart);

            return ifNullReturn(number);
        }
        throw new InvalidOperationException(operation);
    }

    private String ifNullReturn(Long number) {
        return number == null ? "0" : String.valueOf(number);
    }
}
