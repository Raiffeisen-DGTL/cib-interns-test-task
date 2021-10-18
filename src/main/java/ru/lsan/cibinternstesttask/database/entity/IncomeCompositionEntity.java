package ru.lsan.cibinternstesttask.database.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "income_composition")
public class IncomeCompositionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "good_id", nullable = false)
    private GoodEntity good;

    @Column(name = "count", nullable = false)
    private Integer count;

    @ManyToOne
    @JoinColumn(name = "income_id", nullable = false)
    private IncomeEntity income;

}
