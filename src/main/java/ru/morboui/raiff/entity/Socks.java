package ru.morboui.raiff.entity;

import lombok.*;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
@Table(name = "socks")
public class Socks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String color;

    @Column
    private Long cottonPart;

    @Column
    private Long quantity;

}
