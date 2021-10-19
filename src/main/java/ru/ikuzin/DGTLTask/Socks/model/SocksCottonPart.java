package ru.ikuzin.DGTLTask.Socks.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "socks_cotton_part")
@NoArgsConstructor
@AllArgsConstructor
public class SocksCottonPart {
    @Id
    @Column(name = "part")
    private Integer part;
}
