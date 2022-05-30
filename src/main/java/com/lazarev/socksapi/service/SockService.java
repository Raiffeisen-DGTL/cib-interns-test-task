package com.lazarev.socksapi.service;

import com.lazarev.socksapi.dto.SockDto;
import com.lazarev.socksapi.entity.Sock;


public interface SockService {
    Sock addSocks(SockDto dto);
    Sock subSocks(SockDto dto);
    Integer countRequestMatchingSocks(String color, String operation, Integer cottonPart);
}
