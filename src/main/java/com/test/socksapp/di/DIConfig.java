package com.test.socksapp.di;

import com.test.socksapp.repository.SockRepository;
import com.test.socksapp.usecase.GetCount;
import com.test.socksapp.usecase.GetSocks;
import com.test.socksapp.usecase.RegIncome;
import com.test.socksapp.usecase.RegOutcome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DIConfig {
    private SockRepository sockRepository;

    @Autowired
    public DIConfig(SockRepository sockRepository) {
        this.sockRepository = sockRepository;
    }

    @Bean
    public GetCount getCount() {
        return new GetCount(sockRepository);
    }

    @Bean
    public RegIncome regIncome() {
        return new RegIncome(sockRepository);
    }

    @Bean
    public RegOutcome regOutcome() {
        return new RegOutcome(sockRepository);
    }

    @Bean
    public GetSocks getSocks() {
        return new GetSocks(sockRepository);
    }
}
