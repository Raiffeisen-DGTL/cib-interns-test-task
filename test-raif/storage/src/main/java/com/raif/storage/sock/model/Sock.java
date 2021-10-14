package com.raif.storage.sock.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "sock",
        indexes = {@Index(name = "sock_color_cotton_part_idx", columnList = "color, cotton_part")})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sock {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", columnDefinition = "BIGINT")
    @ToString.Exclude
    private long id;

    @Column(name = "color", columnDefinition = "VARCHAR(400)", nullable = false)
    private String color;

    @Column(name = "cotton_part", columnDefinition = "INTEGER", nullable = false)
    private int cottonPart;

    @Column(name = "quantity", columnDefinition = "INTEGER", nullable = false)
    private int quantity;

}
