package ru.raiffeisen.shopwarehouse.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "socks")
public class Socks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private long socksId;
    @Column(name = "color")
    private String color;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "operation")
    private String operation;
    @Column(name = "cotton_part")
    private int cottonPart;
}
