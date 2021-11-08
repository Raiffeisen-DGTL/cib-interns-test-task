package com.raiffeisendgtl.ApiSocks.components;

import com.raiffeisendgtl.ApiSocks.repositories.SocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;

@Component
@Transactional
public class FinderMoreThan implements FinderOperation {

    private SocksRepository socksRepository;

    @Autowired
    public void setSocksRepository(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    @Override
    public Integer findCount(String color, int cottonPart) {
        return socksRepository.findCountSocksMoreThan(color, cottonPart);
    }

}
