package ru.pkaranda.cibinternstesttask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pkaranda.cibinternstesttask.model.domain.OperationType;
import ru.pkaranda.cibinternstesttask.model.domain.SocksTransaction;
import ru.pkaranda.cibinternstesttask.model.domain.TransactionType;
import ru.pkaranda.cibinternstesttask.repository.SocksTransactionRepository;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class SockColorService {

    private final SocksTransactionRepository socksTransactionRepository;


    public int getNumberOfSocksByColorIdAndCottonPart(Long colorId, OperationType operationType, int cottonPart) {

        switch (operationType) {
            case MORE_THAN:
                return countNumberOfSocks(socksTransactionRepository
                        .getSocksTransactionsByColorIdAndCottonPartGreaterThan(colorId, cottonPart));


            case LESS_THAN:
                return countNumberOfSocks(socksTransactionRepository
                        .getSocksTransactionsByColorIdAndCottonPartLessThan(colorId, cottonPart));


            case EQUAL:
                return countNumberOfSocks(socksTransactionRepository
                        .getSocksTransactionsByColorIdAndCottonPartEquals(colorId, cottonPart));

        }

        return 0;

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
