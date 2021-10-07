package com.example.testtask.store.entities;


import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "socks")
public class SocksEntity {

    //@Builder.Default
    @Builder.Default
    private String color = "";

    //@Builder.Default
    @Builder.Default
    private Integer cottonPart = 0;

    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    //@GeneratedValue(strategy=GenerationType.AUTO)
    @Id
    private Integer id;

    @Builder.Default
    private Long quantity = 0l;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();


}
