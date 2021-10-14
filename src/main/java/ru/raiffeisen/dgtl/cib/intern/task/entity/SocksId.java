package ru.raiffeisen.dgtl.cib.intern.task.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public
class SocksId implements Serializable {

    private String color;

    private Byte cottonPart;
}
