package ru.ikuzin.DGTLTask.Socks.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "socks_color")
@NoArgsConstructor
@AllArgsConstructor
public class SocksColor {
    @Id
    @Column(name = "color")
    private String color;
}
