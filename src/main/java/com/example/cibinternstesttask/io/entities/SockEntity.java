package com.example.cibinternstesttask.io.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "socks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "color", nullable = false, length = 50)
    private String color;

    @Column(name = "cotton_part", nullable = false)
    private int cottonPart;

    @Column(name = "quantity", nullable = false)
    private long quantity;
}
