package ru.raiffeisen.socks.database.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
@IdClass(SockKey.class)
@Entity
@Table(name = "sock")
public class Sock implements Serializable {
    @Id
    @Column(name = "color", nullable = false)
    private String color;

    @Id
    @Column(name = "cotton_part", nullable = false)
    private int cottonPart;

    @Column(name = "quantity", nullable = false)
    private int quantity;

}
