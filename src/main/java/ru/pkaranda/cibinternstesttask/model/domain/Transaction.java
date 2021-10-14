package ru.pkaranda.cibinternstesttask.model.domain;

import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class Transaction {

    private Long id;

    @Enumerated(EnumType.STRING)
    private TransactionType type;
}
