package com.raiffeisen.repository;

import com.raiffeisen.model.Sock;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SockRepository {

    void save(Sock sock);

    void update(Sock sock);

    int findWhereCottonPartBigger(String color, int cottonPart);

    int findWhereCottonPartSmaller(String color, int cottonPart);

    int findWhereCottonPartEqual(String color, int cottonPart);
}
