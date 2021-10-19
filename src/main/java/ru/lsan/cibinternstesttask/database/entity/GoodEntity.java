package ru.lsan.cibinternstesttask.database.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "good")
public class GoodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "cotton_part", nullable = false)
    private Integer cotton_part;

    @OneToMany(mappedBy = "goodIncome",fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private Set<IncomeEntity> income;

    @OneToMany(mappedBy = "goodOutcome",fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private Set<OutcomeEntity> outcome;

    @Column(name = "stored")
    private Integer stored;

}
