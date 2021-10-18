package ru.lsan.cibinternstesttask.database.entity;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Table(name = "delivery")
public class DeliveryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime date_time;

    @OneToMany(mappedBy = "delivery", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private Set<DeliveryCompositionEntity> deliveryCompositions;

}
