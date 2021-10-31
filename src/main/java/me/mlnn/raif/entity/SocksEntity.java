package me.mlnn.raif.entity;

import lombok.*;
import me.mlnn.raif.model.SocksModel;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor

@Table(name = "socks_warehouse")
public class SocksEntity {
    public SocksEntity(SocksModel socks) {
        this.color = socks.getColor();
        this.cottonPart = socks.getCottonPart();
        this.quantity = socks.getQuantity();
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name = "color", nullable = false)
    private String color;
    
    @Column(name = "cotton_part", nullable = false)
    private Integer cottonPart;
    
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
