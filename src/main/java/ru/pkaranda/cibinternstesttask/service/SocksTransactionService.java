package ru.pkaranda.cibinternstesttask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pkaranda.cibinternstesttask.exception.*;
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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SocksTransactionService {

    private final SocksTransactionRepository socksTransactionRepository;
    private final SockColorRepository sockColorRepository;
    private final TransactionRepository transactionRepository;


    public CountResult getNumberOfSocksByColorIdAndCottonPart(String color, String operationType, int cottonPart) {

        if (cottonPart < 0 || cottonPart > 100) {
            throw new NotValidCottonPartValueException(Message.WRONG_COTTON_PART_VALUE, cottonPart);
        }


        int number = 0;
        OperationType operation = OperationType.EMPTY;
        if (operationType != null) {
            operation = OperationType.valueOfLabel(operationType);
            if (operation == null) {
                throw new OperationException(Message.WRONG_OPERATION, operationType);
            }
        }

        Long colorId = sockColorRepository.getSockColorByColor(color).orElseThrow(
                () -> new ColorNotFoundException(Message.COLOR_NOT_FOUND, color)).getId();

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

            case EMPTY:
                number = countNumberOfSocks(socksTransactionRepository.getSocksTransactionsByColorId(colorId));
                break;

        }

        return CountResult.builder()
                .color(color)
                .operation(operationType)
                .cottonPart(cottonPart)
                .quantity(number)
                .build();

    }


    @Transactional
    public SocksTransaction registerIncome(String color, int cottonPart, int quantity) {

        if (cottonPart < 0 || cottonPart > 100) {
            throw new NotValidCottonPartValueException(Message.WRONG_COTTON_PART_VALUE, cottonPart);
        }

        if (quantity < 0) {
            throw new NotValidQuantityValueException(Message.WRONG_QUANTITY_VALUE, quantity);
        }

        SocksTransaction income;
        Optional<SockColor> sockColor = sockColorRepository.getSockColorByColor(color);

        if (sockColor.isPresent()) {
            income = SocksTransaction.builder()
                    .transactionType(transactionRepository
                            .getTransactionByType(TransactionType.INCOME)
                            .orElseThrow())
                    .color(sockColor.get())
                    .cottonPart(cottonPart)
                    .quantity(quantity)
                    .build();

        } else {
            income = SocksTransaction.builder()
                    .transactionType(transactionRepository
                            .getTransactionByType(TransactionType.INCOME)
                            .orElseThrow())
                    .color(sockColorRepository
                            .getSockColorByColor(color)
                            .orElse(sockColorRepository.saveAndFlush(
                                    SockColor.builder()
                                            .color(color)
                                            .build()
                            )))
                    .cottonPart(cottonPart)
                    .quantity(quantity)
                    .build();
        }

        socksTransactionRepository.save(income);
        socksTransactionRepository.flush();

        return income;
    }


    @Transactional
    public SocksTransaction registerOutcome(String color, int cottonPart, int quantity) {

        if (cottonPart < 0 || cottonPart > 100) {
            throw new NotValidCottonPartValueException(Message.WRONG_COTTON_PART_VALUE, cottonPart);
        }

        if (quantity < 0) {
            throw new NotValidQuantityValueException(Message.WRONG_QUANTITY_VALUE, quantity);
        }

        SockColor sockColor = sockColorRepository
                .getSockColorByColor(color)
                .orElseThrow(() -> new ColorNotFoundException(Message.COLOR_NOT_FOUND, color));
        if (countNumberOfSocks(socksTransactionRepository.getSocksTransactionsByColorIdAndCottonPartEquals(sockColor.getId(), cottonPart)) - quantity > 0) {
            SocksTransaction outcome = SocksTransaction.builder()
                    .transactionType(transactionRepository
                            .getTransactionByType(TransactionType.OUTCOME)
                            .orElseThrow())
                    .color(sockColor)
                    .cottonPart(cottonPart)
                    .quantity(quantity)
                    .build();

            socksTransactionRepository.save(outcome);
            socksTransactionRepository.flush();
            return outcome;
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
