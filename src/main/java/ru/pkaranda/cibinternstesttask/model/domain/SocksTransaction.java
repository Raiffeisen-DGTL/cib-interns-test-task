package ru.pkaranda.cibinternstesttask.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@Data
@Builder
@Entity
@Table(name = "socks_transactions")
@NoArgsConstructor
@AllArgsConstructor
public class SocksTransaction {

    @Id
    @SequenceGenerator(sequenceName = "seq_socks_transaction_id", name = "socks_transaction_id_gen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "socks_transaction_id_gen")
    private Long id;

    @Column(name = "color_id", insertable = false, updatable = false)
    private Long colorId;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private SockColor color;

    @Column(name = "transaction_type_id", insertable = false, updatable = false)
    private Long transactionTypeId;

    @ManyToOne
    @JoinColumn(name = "transaction_type_id")
    private Transaction transactionType;

    @Min(0)
    @Max(100)
    private int cottonPart;

    @Min(0)
    private int quantity;

}
