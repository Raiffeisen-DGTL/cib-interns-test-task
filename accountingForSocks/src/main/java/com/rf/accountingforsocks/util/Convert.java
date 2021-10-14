package com.rf.accountingforsocks.util;

import com.rf.accountingforsocks.dto.ColorDto;
import com.rf.accountingforsocks.dto.SocksDto;
import com.rf.accountingforsocks.entity.Color;
import com.rf.accountingforsocks.entity.Socks;

public class Convert {
    public static SocksDto socksToDto(Socks socks) {
        SocksDto dto = new SocksDto(socks.getId(), socks.getColor().getName(), socks.getCottonPart(), socks.getQuantity());
        return dto;
    }

    public static Socks dtoToSocks(SocksDto dto) {
        Socks socks = new Socks(dto.getId(), new Color(null, dto.getColor()), dto.getCottonPart(), dto.getQuantity());
        return socks;
    }

    public static Socks dtoToSocks(SocksDto dto, ColorDto colorDto) {
        Socks socks = new Socks(dto.getId(), Convert.dtoToColor(colorDto), dto.getCottonPart(), dto.getQuantity());
        return socks;
    }

    public static Color dtoToColor(ColorDto dto) {
        Color color = new Color(dto.getId(), dto.getColor());
        return color;
    }

    public static ColorDto colorToDto(Color color) {
        ColorDto dto = new ColorDto(null, color.getName());
        return  dto;
    }
}
