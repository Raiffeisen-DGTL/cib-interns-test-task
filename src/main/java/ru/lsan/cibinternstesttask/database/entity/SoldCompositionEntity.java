package ru.lsan.cibinternstesttask.database.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "sold_composition")
public class SoldCompositionEntity {

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
    @JoinColumn(name = "sold_id", nullable = false)
    private SoldEntity sold;

}
