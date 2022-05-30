package com.lazarev.socksapi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Accessors(chain = true)

@Entity
@Table(name = "cotton_parts")
public class CottonPart {

    @Id
    @SequenceGenerator(name = "cotton_parts_seq", sequenceName = "cotton_parts_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cotton_parts_seq")
    private Long id;

    @Column(name = "cotton_part", nullable = false)
    private Integer cottonPart;
}
