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
@Table(name = "sock")
public class SockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "cotton_part", nullable = false)
    private Integer cotton_part;

    @OneToMany(mappedBy = "sockIncome",fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private Set<IncomeEntity> income;

    @OneToMany(mappedBy = "sockOutcome",fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private Set<OutcomeEntity> outcome;

    @Column(name = "stored")
    private Integer stored;

}
