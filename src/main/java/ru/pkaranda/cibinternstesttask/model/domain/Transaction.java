package ru.pkaranda.cibinternstesttask.model.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "transaction_types")
public class Transaction {

    @Id
    @SequenceGenerator(sequenceName = "seq_transaction_type_id", name = "transaction_type_id_gen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_type_id_gen")
    private Long id;

    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private TransactionType type;
}
