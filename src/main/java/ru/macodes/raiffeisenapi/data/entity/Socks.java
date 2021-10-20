package ru.macodes.raiffeisenapi.data.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.macodes.raiffeisenapi.data.dto.SocksDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Socks {

    @Id
    @GeneratedValue
    private long ID;

    @NotNull
    private String color;

    private byte cottonPart;

    private int quantity;

    public static Socks getSocksFromDTO(SocksDto socksDto) {
        return Socks.builder()
                .color(socksDto.getColor())
                .cottonPart(socksDto.getCottonPart())
                .quantity(socksDto.getQuantity())
                .build();
    }
}
