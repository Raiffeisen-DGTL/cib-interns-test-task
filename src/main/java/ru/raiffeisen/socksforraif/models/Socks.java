package ru.raiffeisen.socksforraif.models;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"color", "cottonPart"})})
@Accessors(chain = true)
public class Socks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column
    private String color;

    @Column
    private Byte cottonPart;

    @Column
    private Integer quantity;
}
