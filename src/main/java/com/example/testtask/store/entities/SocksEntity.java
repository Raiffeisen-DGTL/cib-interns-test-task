package com.example.testtask.store.entities;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "socks")
public class SocksEntity {

    @Builder.Default
    private String color;

    @Builder.Default
    private Double cotton;

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    Long id;
   



}
