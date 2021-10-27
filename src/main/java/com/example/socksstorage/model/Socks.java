package com.example.socksstorage.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;

@Configuration
@Entity
@Data
@NoArgsConstructor
@Table(name = "socks")
public class Socks {

    public enum Operation {
        moreThan,
        lessThan,
        equal
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "color")
    private String color;

    @Column(name = "cotton_part")
    private int cottonPart;
}
