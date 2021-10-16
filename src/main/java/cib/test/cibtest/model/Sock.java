package cib.test.cibtest.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Data
@Table(name = "Socks")
@Entity
public class Sock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String color;

    @Column
    private Long cottonPart;

    @Column
    private Long quantity;
}
