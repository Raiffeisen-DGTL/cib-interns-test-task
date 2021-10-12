package com.oleg.socks.validator;

import com.oleg.socks.dto.SocksDto;
import com.oleg.socks.exception.NotValidInputException;

public class SocksDtoValidator {
    public static void validateSocksDto(SocksDto socksDto) {
        if (!CottonPartValidator.isValid(socksDto.getCottonPart())) {throw new NotValidInputException("cotton percentage is not valid");}
        if (!QuantityValidator.isValid(socksDto.getQuantity())) {throw new NotValidInputException("quantity is not valid");}
    }
}
