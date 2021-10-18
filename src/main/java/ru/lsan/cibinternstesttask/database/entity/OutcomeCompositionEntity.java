package ru.lsan.cibinternstesttask.database.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "outcome_composition")
public class OutcomeCompositionEntity {

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
    @JoinColumn(name = "outcome_id", nullable = false)
    private OutcomeEntity outcome;

}
