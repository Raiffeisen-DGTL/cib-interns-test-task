package ru.itis.accountingSocks.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.accountingSocks.models.Socks;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocksDto {

    private Long id;
    private String color;
    private Integer cottonPart;
    private Integer quantity;

    public static SocksDto from(Socks socks) {
        return SocksDto.builder()
                .id(socks.getId())
                .color(socks.getColor().toString())
                .cottonPart(socks.getCottonPart())
                .quantity(socks.getQuantity())
                .build();
    }

    public static List<SocksDto> from(List<Socks> socks) {
        return socks.stream().map(SocksDto::from).collect(Collectors.toList());
    }

}
