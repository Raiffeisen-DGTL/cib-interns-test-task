package com.java.task.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "color")
@Data
@NoArgsConstructor
public class Color {
    @Id
    @Column(name = "color_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "color")
    private String color;

    public Color(String color) {
        this.color = color;
    }
}
