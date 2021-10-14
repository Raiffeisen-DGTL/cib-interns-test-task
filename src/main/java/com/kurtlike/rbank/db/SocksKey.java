package com.kurtlike.rbank.db;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;

@EqualsAndHashCode
@ToString
public class SocksKey implements Serializable {
    private static final long serialVersionUID = 7063787474085184326L;

    @Getter
    @Setter
    private String color;

    @Getter
    @Setter
    private int cottonpart;

}