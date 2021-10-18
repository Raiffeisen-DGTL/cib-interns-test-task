package ru.lsan.cibinternstesttask.database.entity;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "good")
public class GoodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "color", nullable = false)
    private String color;

    @OneToMany(mappedBy = "good", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private Set<CompositionEntity> compositions;

    @OneToMany(mappedBy = "good",fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private Set<DeliveryCompositionEntity> deliveryCompositions;
}
