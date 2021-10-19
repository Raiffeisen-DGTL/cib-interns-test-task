package ru.lsan.cibinternstesttask.database.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "income")
public class IncomeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime date_time;

    @ManyToOne
    @JoinColumn(name = "good_id", nullable = false)
    private GoodEntity goodIncome;

    @Column(name ="quantity", nullable = false)
    private int quantity;


}
