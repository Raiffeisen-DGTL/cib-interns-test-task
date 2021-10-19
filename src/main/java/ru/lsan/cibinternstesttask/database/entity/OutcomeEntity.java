package ru.lsan.cibinternstesttask.database.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "outcome")
public class OutcomeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime date_time;

    @ManyToOne
    @JoinColumn(name = "good_id", nullable = false)
    private GoodEntity goodOutcome;

    @Column(name ="quantity", nullable = false)
    private int quantity;

}
