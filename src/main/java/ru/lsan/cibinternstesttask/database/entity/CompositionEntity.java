package ru.lsan.cibinternstesttask.database.entity;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "composition")
public class CompositionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "fabric")
    private String fabric;

    @Column(name = "percent")
    private double percent;

    @ManyToOne
    @JoinColumn(name = "good_id", nullable = false)
    private GoodEntity good;

}
