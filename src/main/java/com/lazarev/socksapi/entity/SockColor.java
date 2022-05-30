package com.lazarev.socksapi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Accessors(chain = true)

@Entity
@Table(name = "sock_colors")
public class SockColor {
    @Id
    @SequenceGenerator(name = "sock_colors_seq", sequenceName = "sock_colors_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sock_colors_seq")
    private Long id;

    @Column(name = "color", nullable = false)
    private String color;
}
