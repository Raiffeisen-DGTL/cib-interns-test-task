package com.intern.sock.model;
import lombok.Data;
import javax.persistence.*;

@Data
@Table
@Entity
public class Sock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String color;
    @Column
    private Long cottonPart;
    @Column
    private Long quantity;
}
