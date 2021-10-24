package model.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Sock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String color;

    private int cottonPart;

    private int quantity;
}
