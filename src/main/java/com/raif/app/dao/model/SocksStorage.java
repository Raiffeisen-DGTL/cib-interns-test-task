package com.raif.app.dao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("SOCKS_STORAGE")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SocksStorage {

    @Id
    @Column("id")
    private Long id;

    @Column("color")
    private String color;

    @Column("quantity")
    private Long quantity;

    @Column("cotton_part")
    private Integer cottonPart;
}
