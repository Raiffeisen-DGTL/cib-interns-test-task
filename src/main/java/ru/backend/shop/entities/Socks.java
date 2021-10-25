package ru.backend.shop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "socks")
public class Socks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "socks_id")
    private Long socksId;

    @Column(name = "cotton_part")
    private Integer cottonPart;

    @Column(name = "quantity")
    private Integer quantity;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "color_id", insertable = true, updatable = false)
    private Color color;
}