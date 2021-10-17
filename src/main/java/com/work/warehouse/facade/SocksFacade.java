package com.work.warehouse.facade;

import com.work.warehouse.dto.SocksDTO;
import com.work.warehouse.entities.Socks;
import com.work.warehouse.entities.enums.EColor;
import org.springframework.stereotype.Component;

@Component
public class SocksFacade {

    public SocksDTO socksToSocksDTO(Socks socks){
        SocksDTO socksDTO = new SocksDTO();
        socksDTO.setId(socks.getId());
        socksDTO.setColor(EColor.valueOf(socks.getColor()));
        socksDTO.setCottonPart(socks.getCottonPart());
        socksDTO.setQuantity(socks.getQuantity());

        return socksDTO;
    }
}
