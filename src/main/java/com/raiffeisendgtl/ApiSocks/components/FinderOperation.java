package com.raiffeisendgtl.ApiSocks.components;

import com.raiffeisendgtl.ApiSocks.repositories.SocksRepository;

public interface FinderOperation {

    void setSocksRepository(SocksRepository socksRepository);

    Integer findCount(String color, int cottonPart);

}
