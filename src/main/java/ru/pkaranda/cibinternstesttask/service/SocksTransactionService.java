package ru.pkaranda.cibinternstesttask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pkaranda.cibinternstesttask.exception.ColorNotFoundException;
import ru.pkaranda.cibinternstesttask.exception.NotEnoughSocksException;
import ru.pkaranda.cibinternstesttask.model.CountResult;
import ru.pkaranda.cibinternstesttask.model.Message;
import ru.pkaranda.cibinternstesttask.model.domain.OperationType;
import ru.pkaranda.cibinternstesttask.model.domain.SockColor;
import ru.pkaranda.cibinternstesttask.model.domain.SocksTransaction;
import ru.pkaranda.cibinternstesttask.model.domain.TransactionType;
import ru.pkaranda.cibinternstesttask.repository.SockColorRepository;
import ru.pkaranda.cibinternstesttask.repository.SocksTransactionRepository;
import ru.pkaranda.cibinternstesttask.repository.TransactionRepository;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class SocksTransactionService {

    private final SocksTransactionRepository socksTransactionRepository;
    private final SockColorRepository sockColorRepository;
    private final TransactionRepository transactionRepository;


    public CountResult getNumberOfSocksByColorIdAndCottonPart(String color, String operationType, int cottonPart) {

        int number = 0;
        OperationType operation = OperationType.valueOf(operationType);
        Long colorId = sockColorRepository.getSockColorByColor(color).orElseThrow().getId();

        switch (operation) {
            case MORE_THAN:
                number = countNumberOfSocks(socksTransactionRepository
                        .getSocksTransactionsByColorIdAndCottonPartGreaterThan(colorId, cottonPart));
                break;


            case LESS_THAN:
                number = countNumberOfSocks(socksTransactionRepository
                        .getSocksTransactionsByColorIdAndCottonPartLessThan(colorId, cottonPart));
                break;


            case EQUAL:
                number = countNumberOfSocks(socksTransactionRepository
                        .getSocksTransactionsByColorIdAndCottonPartEquals(colorId, cottonPart));
                break;

        }

        return CountResult.builder()
                .color(color)
                .cottonPart(cottonPart)
                .quantity(number)
                .build();

    }


    @Transactional
    public SocksTransaction registerIncome(String color, int cottonPart, int quantity) {

        return socksTransactionRepository.save(SocksTransaction.builder()
                .transactionType(transactionRepository
                        .getTransactionByType(TransactionType.INCOME)
                        .orElseThrow())
                .color(sockColorRepository
                        .getSockColorByColor(color)
                        .orElse(sockColorRepository.save(
                                SockColor.builder()
                                        .color(color)
                                        .build()
                        )))
                .cottonPart(cottonPart)
                .quantity(quantity)
                .build());
    }


    @Transactional
    public SocksTransaction registerOutcome(String color, int cottonPart, int quantity) {

        SockColor sockColor = sockColorRepository
                .getSockColorByColor(color)
                .orElseThrow(() -> new ColorNotFoundException(Message.COLOR_NOT_FOUND, color));
        if (countNumberOfSocks(socksTransactionRepository.getSocksTransactionsByColorIdAndCottonPartEquals(sockColor.getId(), cottonPart)) - quantity > 0) {
            return socksTransactionRepository.save(SocksTransaction.builder()
                    .transactionType(transactionRepository
                            .getTransactionByType(TransactionType.OUTCOME)
                            .orElseThrow())
                    .color(sockColor)
                    .cottonPart(cottonPart)
                    .quantity(quantity)
                    .build());
        } else {
            throw new NotEnoughSocksException(Message.NOT_ENOUGH_SOCKS, color);
        }

    }

    private int countNumberOfSocks(Collection<SocksTransaction> transactions) {

        int number = 0;

        for (SocksTransaction transaction : transactions) {
            if (transaction.getTransactionType().getType().equals(TransactionType.INCOME)) {
                number += transaction.getQuantity();
            } else if (transaction.getTransactionType().getType().equals(TransactionType.OUTCOME)) {
                number -= transaction.getQuantity();
            }

        }

        return number;
    }

}
