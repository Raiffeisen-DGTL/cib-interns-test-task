package ru.tshtk.accounting.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "socks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Socks {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "color")
    private String color;
    @Column(name = "cotton_part")
    private int cottonPart;
    @Column(name = "quantity")
    private int quantity;
}
