package com.vadim01er.taskraiffeisenbank.mapper.impl;

import com.vadim01er.taskraiffeisenbank.dto.SocksDto;
import com.vadim01er.taskraiffeisenbank.entity.Socks;
import com.vadim01er.taskraiffeisenbank.mapper.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SocksMapper extends AbstractMapper<Socks, SocksDto> {

    public SocksMapper(ModelMapper mapper) {
        super(mapper, Socks.class, SocksDto.class);
    }


}
