package com.rareart.socksservice.service;

import com.rareart.socksservice.dto.SocksDto;
import com.rareart.socksservice.dto.request.SocksParamsRequest;
import com.rareart.socksservice.exceptions.InvalidRequestParamsException;
import com.rareart.socksservice.exceptions.NotEnoughItemsException;
import com.rareart.socksservice.exceptions.SocksNotFoundException;

public interface SocksService {

    SocksDto incomeSocks(SocksDto socksDto);

    SocksDto outcomeSocks(SocksDto socksDto) throws SocksNotFoundException, NotEnoughItemsException;

    long getSocksCountByParams(SocksParamsRequest params) throws InvalidRequestParamsException;
}
