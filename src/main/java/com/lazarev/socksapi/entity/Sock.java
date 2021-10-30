package com.lazarev.socksapi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;
import static org.hibernate.annotations.CascadeType.SAVE_UPDATE;

@Data
@NoArgsConstructor
@Accessors(chain = true)

@Entity
@Table(name = "socks")
public class Sock {

    @Id
    @SequenceGenerator(name = "sock_seq", sequenceName = "sock_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sock_seq")
    private Long id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "cotton_part_id", referencedColumnName = "id")
    private CottonPart cottonPart;

    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "sock_color_id", referencedColumnName = "id")
    private SockColor sockColor;

}
