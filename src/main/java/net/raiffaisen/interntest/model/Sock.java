package net.raiffaisen.interntest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "socks")
@JsonIgnoreProperties(value = {"created_at"},
        allowGetters = true)
public class Sock {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name = "color")
    private String color;

    @NotNull
    @Range(min=0, max=100)
    @Column(name = "cotton_part")
    private int cottonPart;

    @NotNull
    @PositiveOrZero
    @Column(name = "quantity")
    private int quantity;

    @Column(name = "created_at",nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date created_at;

//    @Override
//    public String toString() {
//        return "Tutorial [id=" + id + ", color=" + color
//                + ", cottonPart=" + cottonPart + ", quantity="
//                + quantity + " created_at= " + created_at + "]";
//    }
}
