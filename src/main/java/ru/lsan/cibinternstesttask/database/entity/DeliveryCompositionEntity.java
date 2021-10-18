package ru.lsan.cibinternstesttask.database.entity;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "delivery_composition")
public class DeliveryCompositionEntity {

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
    @JoinColumn(name = "delivery_id", nullable = false)
    private DeliveryEntity delivery;

}
