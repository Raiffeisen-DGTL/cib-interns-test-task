package com.raiffeizen.demo.models;

import lombok.*;
import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Socks{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(updatable = false, name = "color")
    private String color;

    @Column(updatable = false, name = "cottonPart")
    private int cottonPart;

    private long quantity;
}
